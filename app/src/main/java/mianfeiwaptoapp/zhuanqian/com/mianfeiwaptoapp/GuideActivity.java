package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity{
    ArrayList<View> contents = new ArrayList<>();
    ViewPager viewPager;
    ArrayList<String>  names;
    private AdView mAdView,adViewrectangle;
    TextView pageControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaide);
        pageControl = findViewById(R.id.pagecontrol);

        viewPager = findViewById(R.id.viewPager);
        mAdView = findViewById(R.id.adView);
        adViewrectangle = findViewById(R.id.adViewrectangle);

        loadAD(adViewrectangle);
        loadAD(mAdView);
        names = new ArrayList<>();
        names.add("这是一款完全免费的app，用来送亲朋送好友送情人，老少皆宜。来来来！在这可以滑动。");
        names.add("注意：这款app可以锁定一个主页地址，锁定之后，只能在app管理中清除数据，方可启用重设大法！");
        names.add("注意：这款app可以定制主页的标题。你进入主页有点击标题，方可编辑。输入框为空，我可是不会改的哦！");
        names.add("注意：此款app有密码锁，后面的步骤会邀请你设置。你锁定的主页只希望你能看到，比如XXX,你懂的。要重设密码，还是请到应用设置清除数据");
        names.add("注意：清除数据之后，以前的设置什么都没有了，请记住你锁定的主页地址");
        for (int i = 0; i <names.size() ; i++) {
            TextView textView = new TextView(GuideActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setTextColor(Color.RED);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER);
            textView.setText(names.get(i));
            contents.add(textView);
        }
        for (int i = 0; i <2 ; i++) {
            View inputLayout =  getLayoutInflater().inflate(R.layout.inout_layout,null);
            contents.add(inputLayout);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageControl.setText(position+"/"+(names.size()+2));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyAdapter());
        viewPager.setCurrentItem(0);
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container,final int position) {

            viewPager.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewPager.setEnabled(true);
                }
            },2000);
            Log.e("----instantiateItem", "instantiateItem");
            View view = contents.get(position);
            container.addView(view);
            if(position<names.size()){
                return view;
            }
            final EditText editText = view.findViewById(R.id.edittext);
            Button button =  view.findViewById(R.id.btn);
            if(position==names.size()){
                button.setText("老猿，给我记住这个密码");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Util.setPSW(GuideActivity.this,editText.getText().toString().trim());
                        new AlertDialog.Builder(GuideActivity.this).setCustomTitle(ADTool.getBannerRectangle(GuideActivity.this)).setMessage("你如果你输入为空，就是没有密码！").setNegativeButton("老猿废话多", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewPager.setCurrentItem(position+1);
                            }
                        }).setPositiveButton("重新输入",null ).show();
                    }
                });
            }else if(position==names.size()+1){
                button.setText("老猿，给我记住这个网站");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new AlertDialog.Builder(GuideActivity.this).setCustomTitle(ADTool.getBannerRectangle(GuideActivity.this)).setMessage("请检查好URL,如果随便乱输，后果自行负责！").setPositiveButton("重新输入\n",null).setNegativeButton("好了,进入主页吧!\n老猿gun远点~~", new DialogInterface.OnClickListener() {

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
            }
            return view;
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

    @Override
    protected void refreshAD() {
        loadAD(adViewrectangle);
        loadAD(mAdView);
    }
}
