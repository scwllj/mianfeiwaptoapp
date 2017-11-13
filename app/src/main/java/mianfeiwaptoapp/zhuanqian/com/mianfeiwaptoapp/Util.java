package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 李宏阳 on 2017/11/10.
 */

public class Util {

    public interface TimeToRefresh{
        void onRefresh();
    }

    public static HashMap<String,TimeToRefresh> listeners;

    private static final String KEY = "abcdefg1234567890hijklmn";

    private static final String SHA1PRNG = "SHA1PRNG";   // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
    private static final String IV = "qws871bz73msl9x8";
    private static final String AES = "AES";   //AES 加密
    private static final String CIPHERMODE = "AES/CBC/PKCS5Padding";   //algorithm/mode/padding

    public static String getUrl(Context context){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        String url = sp.getString("murl","").trim();
        if(!TextUtils.isEmpty(url)){
            url = decriptUrl(url);
        }
        return url;
    }

    private static String decriptUrl(String src){
        try {
            return new String(Base64.decode(src.getBytes(), Base64.DEFAULT));
        }catch (Exception ex){

        }
        return  "";
    }


    public static void setUrl(Context context,String url){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("murl",Base64.encodeToString(url.getBytes(), Base64.DEFAULT));
        editor.commit();
    }

    public static void resetUrl(Context context){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("murl","");
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


    public static void setPSW(Context context,String psw){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("asdasdw",""+MD5Encript(psw));
        editor.commit();
    }

    public static boolean checkPSW(Context context,String psw){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        String oldPsw = sp.getString("asdasdw","");
        if(TextUtils.isEmpty(oldPsw)){
            return true;
        }
        if(TextUtils.isEmpty(psw)){
            return false;
        }
        if(oldPsw.equals(MD5Encript(psw))){
            return true;
        }
        return false;
    }

    public static boolean hasPSW(Context context){
        SharedPreferences sp = context.getSharedPreferences("myConfig",Context.MODE_PRIVATE);
        String oldPsw = sp.getString("asdasdw","");
        if(!TextUtils.isEmpty(oldPsw)){
            return true;
        }
        return false;
    }

    /**
     * 加密
     */
    public static String encrypt(String key, String cleartext) {
        if (TextUtils.isEmpty(cleartext)) {
            return cleartext;
        }
        try {
            byte[] result = encrypt(key, cleartext.getBytes());
            return parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     */
    public static byte[] encrypt(String key, byte[] clear) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CIPHERMODE);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    /**
     * 解密
     */
    public static String decrypt(String key, String encrypted) {
        if (TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }
        try {
            byte[] enc = parseHexStr2Byte(encrypted);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     */
    public static byte[] decrypt(String key, byte[] encrypted) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CIPHERMODE);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    /**
     * 生成随机数，可以当做动态的密钥
     * 加密和解密的密钥必须一致，不然将不能解密
     */
    public static String generateKey() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] key = new byte[20];
            secureRandom.nextBytes(key);
            return toHex(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *     对密钥进行处理
     */
    public static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        //for android
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
        } else {
            sr = SecureRandom.getInstance(SHA1PRNG);
        }
        // for Java
        // secureRandom = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(seed);
        kgen.init(128, sr); //256 bits or 128 bits,192bits
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /**
     *   二进制转字符
     */
    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(IV.charAt((b >> 4) & 0x0f)).append(IV.charAt(b & 0x0f));
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void addListener(String key,TimeToRefresh timeToRefresh){
        if(listeners==null){
            listeners = new HashMap<>();
            startTimer();
        }
        listeners.put(key,timeToRefresh);
    }

    public static void removeListener(String key){
        listeners.remove(key);
    }

    public static void notifyRefresh(){
        for(Map.Entry<String,TimeToRefresh> entry:listeners.entrySet()){
            entry.getValue().onRefresh();
        }
    }

    public static void startTimer(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                notifyRefresh();
            }
        },10*1000,10*1000);
    }

}
