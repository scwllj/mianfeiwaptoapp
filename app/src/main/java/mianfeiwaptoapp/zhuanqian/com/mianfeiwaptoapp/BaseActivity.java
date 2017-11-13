package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by 李宏阳 on 2017/11/13.
 */

public abstract class BaseActivity extends Activity {
    public String refreshKEY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void loadAD(AdView adView){
        adView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshKEY = String.valueOf(System.currentTimeMillis());
        Util.addListener(refreshKEY,new Util.TimeToRefresh() {
            @Override
            public void onRefresh() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshAD();
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.removeListener(refreshKEY);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected abstract void refreshAD();
}
