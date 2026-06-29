package com.example.absensi.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.absensi.helper.Constant;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context){

        pref = context.getSharedPreferences(Constant.PREF_NAME,
                Context.MODE_PRIVATE);

        editor = pref.edit();

    }

    public void saveToken(String token){

        editor.putString(Constant.ACCESS_TOKEN,token);

        editor.apply();

    }

    public String getToken(){

        return pref.getString(Constant.ACCESS_TOKEN,"");

    }

    public void logout(){

        editor.clear();

        editor.apply();

    }

}