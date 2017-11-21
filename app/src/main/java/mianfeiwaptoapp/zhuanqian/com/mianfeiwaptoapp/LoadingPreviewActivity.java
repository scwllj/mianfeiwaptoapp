package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.os.Bundle;

import com.wllj.library.googleloading.GoogleProgressBar;

public class LoadingPreviewActivity extends BaseActivity {




    GoogleProgressBar googleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_preview);

        googleProgressBar = findViewById(R.id.google_progressbar);
        switch ( getIntent().getIntExtra("type",0)){

        }
        googleProgressBar.setStyle(GoogleProgressBar.ProgressType.FOLDING_CIRCLES);
    }

    @Override
    protected void refreshAD() {

    }
}
