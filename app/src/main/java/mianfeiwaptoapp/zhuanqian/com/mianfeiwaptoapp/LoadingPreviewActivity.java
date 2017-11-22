package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.wllj.library.googleloading.GoogleProgressBar;

public class LoadingPreviewActivity extends BaseActivity {



    AdView adView;
    GoogleProgressBar googleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_preview);

        adView = findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        googleProgressBar = findViewById(R.id.google_progressbar);
        switch ( getIntent().getIntExtra("type",0)){
            case 0:

                break;
            case 1:
                googleProgressBar.setStyle(GoogleProgressBar.ProgressType.FOLDING_CIRCLES);
                break;
            case 2:
                googleProgressBar.setStyle(GoogleProgressBar.ProgressType.CHROME_FLOATING_CIRCLES);
                break;
            case 3:
                googleProgressBar.setStyle(GoogleProgressBar.ProgressType.GOOGLE_MUSIC_DICES);
                break;
            case 4:
                googleProgressBar.setStyle(GoogleProgressBar.ProgressType.NEXUS_ROTATION_CROSS);
                break;
        }
        googleProgressBar.setStyle(GoogleProgressBar.ProgressType.FOLDING_CIRCLES);
    }

    @Override
    protected void refreshAD() {

    }
}
