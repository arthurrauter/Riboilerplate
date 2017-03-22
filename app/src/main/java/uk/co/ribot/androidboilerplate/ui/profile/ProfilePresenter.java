package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by Arthur on 22/03/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    Ribot mRibot;

    public void ProfilePresenter() {}

    public void loadProfileInfo(Intent intent) {
        checkViewAttached();
        if (intent == null) return;
        if (!intent.hasExtra("PROFILE_INFO")) return;

        mRibot = intent.getParcelableExtra("PROFILE_INFO");

        getMvpView().showProfileInfo(mRibot.profile());
    }

    public void sendEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:" + mRibot.profile().email()));

        getMvpView().sendEmail(i);
    }

}
