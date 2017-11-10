package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 李宏阳 on 2017/11/10.
 */

public class Util {

    public static String getUrl(Context context){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        String url = sp.getString("murl","").trim();
        if(!TextUtils.isEmpty(url)){
            url = decriptUrl(url);
        }
        return url;
    }

    private static String decriptUrl(String src){
        return src;
    }

    public static void setUrl(Context context,String url){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("murl",url);
        editor.commit();
    }

    private static String MD5Encript(String src){
        if (TextUtils.isEmpty(src)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(src.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String MD5Decript(String src){

        return src;
    }


}
