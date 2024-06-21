package com.example.javapro.auth;

import com.example.javapro.enums.UserRole;

import java.util.UUID;

public class UserSession {
    private static UserSession instance;

    private String token = null;
    private String userId = null;
    private UserRole userRole = null;
    private boolean isAuthenticated = false;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        isAuthenticated = this.token != null;
    }

    public void removeToken(){
        token = null;
        userId = null;
        userRole = null;
        isAuthenticated = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
