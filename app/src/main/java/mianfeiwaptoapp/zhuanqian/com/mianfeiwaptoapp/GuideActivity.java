package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GuideActivity extends Activity{
    ArrayList<View> contents = new ArrayList<>();
    ViewPager viewPager;
    private AdView mAdView,adViewrectangle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaide);
        viewPager = findViewById(R.id.viewPager);
        mAdView = findViewById(R.id.adView);
        adViewrectangle = findViewById(R.id.adViewrectangle);
        mAdView.loadAd(new AdRequest.Builder().build());
        adViewrectangle.loadAd(new AdRequest.Builder().build());
        String []names = {"整点广告试试，来来来！在这可以滑动。","第2句话","第3句话","程序猿要少说废话！点击揍他"};
        for (int i = 0; i <names.length ; i++) {
            TextView textView = new TextView(GuideActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setTextColor(Color.RED);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER);
            textView.setText(names[i]);
            if(i==names.length-1){
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GuideActivity.this.startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        finish();
                    }
                });
            }
            contents.add(textView);
        }
        viewPager.setAdapter(new MyAdapter());
        viewPager.setCurrentItem(0);
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e("----instantiateItem", "instantiateItem");
            container.addView(contents.get(position));
            return contents.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e("----destroyItem", "destroyItem");
//            super.destroyItem(container, position, object);
            container.removeView(contents.get(position));
        }

        @Override
        public int getCount() {
            return contents.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

}
