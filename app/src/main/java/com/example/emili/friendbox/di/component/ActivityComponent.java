package com.example.emili.friendbox.di.component;

import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.di.PerActivity;
import com.example.emili.friendbox.di.module.ActivityModule;
import com.example.emili.friendbox.ui.editActivity.LinkEditFragment;
import com.example.emili.friendbox.ui.editActivity.LinkEditModelImpl;
import com.example.emili.friendbox.ui.editActivity.LinkEditPresenterImpl;
import com.example.emili.friendbox.ui.editActivity.LinkViewFragment;
import com.example.emili.friendbox.ui.editActivity.LinkViewModelImpl;
import com.example.emili.friendbox.ui.editActivity.LinkViewPresenterImpl;
import com.example.emili.friendbox.ui.homeActivity.CallMessageFragment;
import com.example.emili.friendbox.ui.homeActivity.GestionFragment;
import com.example.emili.friendbox.ui.homeActivity.NotificationFragment;
import com.example.emili.friendbox.ui.homeActivity.NotificationModelImpl;
import com.example.emili.friendbox.ui.homeActivity.NotificationPresenterImpl;
import com.example.emili.friendbox.ui.homeActivity.ThemesFragment;
import com.example.emili.friendbox.ui.mainActivity.PersonalSignUpInformationFragment;
import com.example.emili.friendbox.ui.mainActivity.SignInFragment;
import com.example.emili.friendbox.ui.mainActivity.SignInModelImpl;
import com.example.emili.friendbox.ui.mainActivity.SignInPresenterImpl;
import com.example.emili.friendbox.ui.mainActivity.SignUpModelImpl;
import com.example.emili.friendbox.ui.mainActivity.SignUpPresenterImpl;
import com.example.emili.friendbox.ui.themeItemsActivity.ThemeItemsActivity;

import dagger.Component;

/**
 * Created by emili on 19/11/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /* SignIn fragment */
    void inject(SignInFragment signInFragment);
    void inject(SignInPresenterImpl signInPresenter);
    void inject(SignInModelImpl signInModel);

    /* Theme fragment */
    void inject(ThemesFragment themesFragment);

    /* Gestion fragment */
    void inject(GestionFragment gestionFragment);

    /* CallMessage fragment */
    void inject(CallMessageFragment callMessageFragment);

    /* ThemeItemes Activity */
    void inject(ThemeItemsActivity themeItemsActivity);

    /* LinkEdit **/
    void inject(LinkEditFragment linkEditFragment);
    void inject(LinkEditPresenterImpl linkEditPresenter);
    void inject(LinkEditModelImpl linkEditModelImpl);

    /*LinkView */
    void inject(LinkViewFragment linkViewFragment);
    void inject(LinkViewPresenterImpl linkViewPresenter);
    void inject(LinkViewModelImpl linkViewModel);

    /*Notification */
    void inject(NotificationFragment notificationFragment);
    void inject(NotificationPresenterImpl notificationPresenter);
    void inject(NotificationModelImpl notificationModel);


    /* SignUp fragment */
    void inject(PersonalSignUpInformationFragment personalSignUpInformationFragment);
    void inject(SignUpPresenterImpl signUpPresenter);
    void inject(SignUpModelImpl signUpModel);
}
