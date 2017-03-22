package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class ActivityProfile extends BaseActivity implements ProfileMvpView {

    ProfilePresenter mProfilePresenter;

    @BindView(R.id.imageViewAvatar) ImageView ivAvatar;
    @BindView(R.id.textViewName) TextView tvName;
    @BindView(R.id.textViewBio) TextView tvBio;
    @BindView(R.id.textViewBirth) TextView tvBirth;
    @BindView(R.id.textViewEmail) TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mProfilePresenter = new ProfilePresenter();
        mProfilePresenter.attachView(this);
        mProfilePresenter.loadProfileInfo(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProfilePresenter.detachView();
    }

    @Override
    public void showProfileInfo(Profile p) {
        Glide.with(this).load(p.avatar()).into(ivAvatar);
        tvName.setText(p.name().first() + " " + p.name().last());
        tvBio.setText(p.bio());
        tvEmail.setText(p.email());
        tvBirth.setText(p.dateOfBirth().toString());
    }
}
