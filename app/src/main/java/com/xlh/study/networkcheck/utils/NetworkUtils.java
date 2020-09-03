package com.xlh.study.networkcheck.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

/**
 * @author: Watler Xu
 * time:2020/9/3
 * description:
 * version:0.0.1
 */
public class NetworkUtils {

    /**
     * 判断是否打开网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvaiable(Context context) {
        boolean isAvaiable = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            isAvaiable = true;
        }
        return isAvaiable;
    }


    /**
     * 跳转到网络设置页面
     *
     * @param context
     */
    public static void goNetSettings(Context context) {
        Intent intent = null;
        // 判断当前系统版本
        if (Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        } else {
            intent = new Intent();
            intent.setClassName("com.android.setting", "com.android.settings.WirelessSettings");
        }
        context.startActivity(intent);
    }

}
