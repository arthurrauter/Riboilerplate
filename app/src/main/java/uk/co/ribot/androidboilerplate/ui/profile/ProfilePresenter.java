package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.Intent;
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

    public void ProfilePresenter() {}

    public void loadProfileInfo(Intent intent) {
        checkViewAttached();
        if (intent == null) return;
        if (!intent.hasExtra("PROFILE_INFO")) return;

        Ribot ribot = intent.getParcelableExtra("PROFILE_INFO");

        getMvpView().showProfileInfo(ribot.profile());
    }

}
