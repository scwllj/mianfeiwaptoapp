package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Loading extends Activity implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
//        mRewardedVideoAd.setRewardedVideoAdListener(this);
//        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                doNext();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        mRewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Loading.this,GuideActivity.class);
                if(!TextUtils.isEmpty(Util.getUrl(Loading.this.getApplicationContext()))){
                    intent = new Intent(Loading.this,MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.e("----onRewarded", ""+rewardItem.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.e("----", "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.e("----", "onRewardedVideoAdFailedToLoad:"+i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());

    }

    private void doNext(){
        Intent intent = new Intent(Loading.this,GuideActivity.class);
        if(!TextUtils.isEmpty(Util.getUrl(Loading.this.getApplicationContext()))){
            intent = new Intent(Loading.this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
