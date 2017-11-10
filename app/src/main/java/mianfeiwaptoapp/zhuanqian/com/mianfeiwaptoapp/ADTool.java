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

    public static AdView getADView(Context context){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(Config.BANNER_RECTANGLE_ID);
        adView.loadAd(new AdRequest.Builder().build());
        return adView;
    }
}
