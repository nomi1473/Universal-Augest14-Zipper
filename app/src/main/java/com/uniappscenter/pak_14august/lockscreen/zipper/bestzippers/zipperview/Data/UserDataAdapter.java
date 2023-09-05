package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Data;


import android.content.Context;
import android.content.SharedPreferences;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;


public class UserDataAdapter {


    public static void saveBG(String pref, String DataToSave, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(pref, DataToSave);
        editor.apply();
    }

    public static String getBG(String pref, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        return sharedPreferences.getString(pref, null);
    }

    public static void saveBlurBG(String pref, String DataToSave, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(pref, DataToSave);
        editor.apply();
    }

    public static String getBlurBG(String pref, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        return sharedPreferences.getString(pref, null);
    }

    public static void saveZipperStyle(String pref, int position, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(pref, position);
        editor.apply();
    }

    public static int getZipperStyle(String pref, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        return sharedPreferences.getInt(pref, 6);
    }

    public static void saveHookStyle(String pref, int position, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(pref, position);
        editor.apply();
    }

    public static int getHookStyle(String pref, Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ConstantData.DataFileName, 0);
        return sharedPreferences.getInt(pref, 1);
    }

}