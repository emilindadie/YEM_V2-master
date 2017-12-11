package com.example.emili.friendbox.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.GetPokemonService;
import com.example.emili.friendbox.service.LinkEditService;
import com.example.emili.friendbox.service.LinkViewService;
import com.example.emili.friendbox.service.NotificationService;
import com.example.emili.friendbox.service.SignInService;
import com.example.emili.friendbox.service.SignUpService;
import com.example.emili.friendbox.ui.editActivity.LinkEditModelImpl;
import com.example.emili.friendbox.ui.editActivity.LinkEditModelCallBack;
import com.example.emili.friendbox.ui.editActivity.LinkEditPresenter;
import com.example.emili.friendbox.ui.editActivity.LinkEditPresenterImpl;
import com.example.emili.friendbox.ui.editActivity.LinkViewModelCallBack;
import com.example.emili.friendbox.ui.editActivity.LinkViewModelImpl;
import com.example.emili.friendbox.ui.editActivity.LinkViewPresenter;
import com.example.emili.friendbox.ui.editActivity.LinkViewPresenterImpl;
import com.example.emili.friendbox.ui.homeActivity.NotificationModelCallBack;
import com.example.emili.friendbox.ui.homeActivity.NotificationModelImpl;
import com.example.emili.friendbox.ui.homeActivity.NotificationPresenter;
import com.example.emili.friendbox.ui.homeActivity.NotificationPresenterImpl;
import com.example.emili.friendbox.ui.homeActivity.ThemesPresenter;
import com.example.emili.friendbox.ui.homeActivity.ThemesPresenterImpl;
import com.example.emili.friendbox.ui.mainActivity.SignInModelCallBack;
import com.example.emili.friendbox.ui.mainActivity.SignInModelImpl;
import com.example.emili.friendbox.ui.mainActivity.SignInPresenter;
import com.example.emili.friendbox.ui.mainActivity.SignInPresenterImpl;
import com.example.emili.friendbox.ui.mainActivity.SignUpModelCallBack;
import com.example.emili.friendbox.ui.mainActivity.SignUpModelImpl;
import com.example.emili.friendbox.ui.mainActivity.SignUpPresenter;
import com.example.emili.friendbox.ui.mainActivity.SignUpPresenterImpl;
import com.example.emili.friendbox.ui.themeItemsActivity.ThemeItemsPresenter;
import com.example.emili.friendbox.ui.themeItemsActivity.ThemeItemsPresenterImpl;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emili on 19/11/2017.
 */

@Module
public class ActivityModule {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    Activity activity(){
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    FirebaseHelper provideFirebaseHelper(){
        return new FirebaseHelper();
    }


    /***SignIn activity fragment **/

    @Provides
    SignInService provideSignInService(@ActivityContext Context context, FirebaseHelper firebaseHelper, SignInModelCallBack signInModelCallBack){
        return new SignInModelImpl(context, firebaseHelper, signInModelCallBack);
    }

    @Provides
    SignInPresenter provideSignInPresenter(SignInService signInService){
        return new SignInPresenterImpl(signInService);
    }

    @Provides
    SignInModelCallBack provideSignInModelCallBack(){
        return new SignInPresenterImpl(null);
    }


    /***SignUp activity fragment **/

    @Provides
    SignUpService provideSignUpService(@ActivityContext Context context, FirebaseHelper firebaseHelper, SignUpModelCallBack signUpModelCallBack){
        return new SignUpModelImpl(context, firebaseHelper, signUpModelCallBack);
    }

    @Provides
    SignUpPresenter provideSignUpPresenter(SignUpService signUpService){
        return new SignUpPresenterImpl(signUpService);
    }

    @Provides
    SignUpModelCallBack provideSignUpModelCallBack(){
        return new SignUpPresenterImpl(null);
    }


    @Provides
    @Named(BASE_URL)
    String provideBaseUrl(){
        return BASE_URL;
    }

    @Provides
    GsonConverterFactory provideGsonFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    Retrofit retrofit(GsonConverterFactory factory, @Named(BASE_URL) String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    GetPokemonService provideGetPokemonService(Retrofit retrofit){
        return retrofit.create(GetPokemonService.class);
    }


    /***Themes fragment **/

    @Provides
    ThemesPresenter provideThemesFragmentPresenter(){
        return new ThemesPresenterImpl();
    }


    @Provides
    ThemeItemsPresenter provideThemeItemesPresenter(){
        return new ThemeItemsPresenterImpl();
    }


    /***Link edit fragment **/

    @Provides
    LinkEditService provideLinkeditService(@ActivityContext Context context, FirebaseHelper firebaseHelper, LinkEditModelCallBack linkEditModelCallBack){
        return new LinkEditModelImpl(context, firebaseHelper, linkEditModelCallBack);
    }

    @Provides
    LinkEditPresenter provideLinkEditPresenter(LinkEditService linkEditService){
        return new LinkEditPresenterImpl(linkEditService);
    }

    @Provides
    LinkEditModelCallBack provideLinkEditModelCallBack(){
        return new LinkEditPresenterImpl(null);
    }


    /***Link view fragment **/

    @Provides
    LinkViewService provideLinkViewService(@ActivityContext Context context, FirebaseHelper firebaseHelper, LinkViewModelCallBack linkViewModelCallBack){
        return new LinkViewModelImpl(context, firebaseHelper, linkViewModelCallBack);
    }

    @Provides
    LinkViewPresenter provideLinkViewPresenter(LinkViewService linkViewService){
        return new LinkViewPresenterImpl(linkViewService);
    }

    @Provides
    LinkViewModelCallBack provideLinkViewModelCallBack(){
        return new LinkViewPresenterImpl(null);
    }




    /***Notification fragment **/
    @Provides
    NotificationService provideNotificationService(@ActivityContext Context context, FirebaseHelper firebaseHelper, NotificationModelCallBack notificationModelCallBack){
        return new NotificationModelImpl(context, firebaseHelper, notificationModelCallBack);
    }

    @Provides
    NotificationPresenter provideNotificationPresenter(NotificationService notificationService){
        return new NotificationPresenterImpl(notificationService);
    }

    @Provides
    NotificationModelCallBack provideNotificationModelCallBack(){
        return new NotificationPresenterImpl(null);
    }

}