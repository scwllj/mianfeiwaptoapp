package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by 李宏阳 on 2017/11/10.
 */

public class ADTool {
    private ADTool adTool;
//    public static ADTool getInstance() {
//        return adTool;
//    }
    static final boolean DEBUG = true;
    public static final String AD_APP_ID ;
    public static final String AD_BANNER ;
    public static final String AD_BANNER_RECTANGLE;
    public static final String AD_FULL_SCREEN ;
    static {
        if(DEBUG){
            AD_APP_ID = "ca-app-pub-3940256099942544~3347511713";
            AD_BANNER = "ca-app-pub-3940256099942544/6300978111";
            AD_BANNER_RECTANGLE = "ca-app-pub-3940256099942544/6300978111";
            AD_FULL_SCREEN = "ca-app-pub-3940256099942544/1033173712";
        }else{
            AD_APP_ID = "ca-app-pub-6037231761951998~5269703510";
            AD_BANNER = "ca-app-pub-6037231761951998/5517115526";
            AD_BANNER_RECTANGLE = "ca-app-pub-6037231761951998/1522988052";
            AD_FULL_SCREEN = "ca-app-pub-6037231761951998/9831765054";
        }
    }


    public static AdView getADView(Context context){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(AD_BANNER_RECTANGLE);
        adView.loadAd(new AdRequest.Builder().build());
        return adView;
    }

    public static AdView getBannerRectangle(Context context){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(AD_BANNER_RECTANGLE);
        adView.loadAd(new AdRequest.Builder().build());
        return adView;
    }
    public static AdView getBannerNormal(Context context){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_BANNER);
        adView.loadAd(new AdRequest.Builder().build());
        return adView;
    }

    public static void loadAD(AdView adView,String adID){
        adView.setAdUnitId(adID);
        adView.loadAd(new AdRequest.Builder().build());
    }

}
