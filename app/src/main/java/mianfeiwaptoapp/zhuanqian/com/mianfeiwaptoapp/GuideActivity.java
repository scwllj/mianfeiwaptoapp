package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GuideActivity extends Activity{
    ArrayList<View> contents = new ArrayList<>();
    ViewPager viewPager;
    private AdView mAdView,adViewrectangle,adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaide);
        adView = new AdView(GuideActivity.this);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(Config.BANNER_RECTANGLE_ID);
        adView.loadAd(new AdRequest.Builder().build());

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
            contents.add(textView);
        }
        View inputLayout =  getLayoutInflater().inflate(R.layout.inout_layout,null);
        final EditText editText = inputLayout.findViewById(R.id.edittext);
        Button button =  inputLayout.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(GuideActivity.this).setCustomTitle(adView).setMessage("请检查好URL,如果随便乱输，后果自行负责！").setNegativeButton("程序猿就是废话多!\n啪~~", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String  ass= editText.getText().toString();
                        Util.setUrl(GuideActivity.this,ass);
                        startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        finish();
                    }
                }).show();
            }
        });
        contents.add(inputLayout);
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
