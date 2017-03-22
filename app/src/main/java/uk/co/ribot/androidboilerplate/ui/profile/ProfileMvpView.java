package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.Intent;

import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by Arthur on 22/03/2017.
 */

public interface ProfileMvpView extends MvpView{

    void showProfileInfo(Profile p);

    void sendEmail(Intent i);
}
