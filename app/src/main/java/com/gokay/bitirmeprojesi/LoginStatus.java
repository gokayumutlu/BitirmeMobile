package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginStatus {
    private String sharedPrefs="sharedPrefs";
    private String loginStatus="loginStatus";
    private String userEmail;


    public void setLoginStatus(Context context,boolean loggedin){
        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefs,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(loginStatus,loggedin);
        editor.apply();

    }

    public void setUser(Context context,String userEmaill){
        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefs,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(userEmail,userEmaill);
        editor.apply();
    }

    public boolean getLoginStatus(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefs,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(loginStatus,false);
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
