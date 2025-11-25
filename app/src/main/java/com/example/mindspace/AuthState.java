package com.example.mindspace;

import android.app.Application;

import com.example.mindspace.api_response.UserProfile;

public class AuthState extends Application {


    private UserProfile userProfile = null;

    private boolean loggedIn = false;


    private void setUserProfile(UserProfile userProfile) {

        this.userProfile = userProfile;

    }

    public void setLoggedIn(boolean loggedIn, UserProfile userProfile) {
        this.loggedIn = loggedIn;
        this.userProfile = userProfile;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    private UserProfile getUserProfile() {
        return userProfile;
    }
}
