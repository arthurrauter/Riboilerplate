package uk.co.ribot.androidboilerplate.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.profile.ActivityProfile;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    private List<Ribot> mRibots;

    @Inject
    public RibotsAdapter() {
        mRibots = new ArrayList<>();
    }

    public void setRibots(List<Ribot> ribots) {
        mRibots = ribots;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        Ribot ribot = mRibots.get(position);

        if (ribot.profile().avatar() != null) {
            //Here I have the URL to the Avatar, if it is available
            Glide.with(holder.avatarView.getContext()).load(ribot.profile().avatar()).into(holder.avatarView);
            //It's the first time I use Glide, but it was conveniently linked in the github description of the android-boilerplate
            //Getting the context from the view feels dirty, but seems to be a simple solution.
            //Other options would be (A) Pass it in the Adapter Constructor or (B) Inject it
            //I can't do A because the Adapter itself is being injected
            //I can't do B because I am not that familiar with Dagger and Injections
        } else {
            holder.avatarView.setBackgroundColor(Color.parseColor(ribot.profile().hexColor()));
        }

        holder.nameTextView.setText(String.format("%s %s",
                ribot.profile().name().first(), ribot.profile().name().last()));
        holder.emailTextView.setText(ribot.profile().email());
        holder.i = position;
    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        public int i;
        @BindView(R.id.card_view) CardView cardView;
        @BindView(R.id.avatar_view) ImageView avatarView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Context context = cardView.getContext();
                    Intent intent = new Intent(context, ActivityProfile.class);
                    intent.putExtra("PROFILE_INFO", mRibots.get(i));
                    context.startActivity(intent);
                }
            });
        }
    }
}
