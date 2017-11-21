package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp.ADTool;
import mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp.GuideActivity;
import mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp.R;

/**
 * Created by Administrator on 2017/11/12.
 */

public class MyADView extends RelativeLayout {

    private int style;

    public MyADView(Context context) {
        this(context,null);
    }

    public MyADView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet,0);
    }

    public MyADView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getAttrs(context,attributeSet);
        initView();
    }


    private void initView(){
        AdView adView = new AdView(getContext());
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(ADTool.AD_BANNER_RECTANGLE);
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT,TRUE);
        addView(adView,params);
        adView.loadAd(new AdRequest.Builder().build());

    }

    public  void loadAD(){

    }

    /**
     * 得到属性值
     *
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.myADView);
        ta.recycle();
    }
}
