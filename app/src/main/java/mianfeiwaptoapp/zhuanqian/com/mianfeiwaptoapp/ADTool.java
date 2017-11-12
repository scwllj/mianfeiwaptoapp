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

    public static final String AD_APP_ID = "ca-app-pub-6037231761951998~5269703510";
    public static final String AD_BANNER = "ca-app-pub-6037231761951998/5517115526";
    public static final String AD_BANNER_RECTANGLE = "ca-app-pub-6037231761951998/1522988052";
    public static final String AD_FULL_SCREEN = "ca-app-pub-6037231761951998/9831765054";

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
