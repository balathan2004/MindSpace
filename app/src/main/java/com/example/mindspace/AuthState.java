package com.example.mindspace;

import android.app.Application;

import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.UserProfile;

public class AuthState extends Application {


    private UserProfile userProfile = null;

    private boolean loggedIn = false;

    private String accessToken;


    private void setUserProfile(UserProfile userProfile) {

        this.userProfile = userProfile;

    }

    public void setLoggedIn(AuthResponseConfig response) {
        this.loggedIn = true;
        this.userProfile = response.getUserProfile();
        this.accessToken = response.getAccessToken();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    private UserProfile getUserProfile() {
        return userProfile;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
