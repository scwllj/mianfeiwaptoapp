package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

    WebView webview;
    ImageView back;
    ImageView home;
    ImageView refresh;
    TextView titleView;
    String HOME="";
    AdView bottomADView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_style1);
        webview = findViewById(R.id.webview);
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);
        refresh = findViewById(R.id.refresh);
        titleView = findViewById(R.id.title);
        bottomADView = findViewById(R.id.adViewbottom);
        bottomADView.loadAd(new AdRequest.Builder().build());
        titleView.setText(getMyTitle());
        HOME = Util.getUrl(this);
        if(!HOME.startsWith("http")){
            new AlertDialog.Builder(this).setCustomTitle(ADTool.getADView(this))
                    .setMessage("你的url不是以http开头的,页面加载不出来,请清除应用数据，重启app").setNegativeButton("我去设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent("com.android.settings");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }).setPositiveButton("帮我重置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Util.resetUrl(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this,Loading.class);
                    startActivity(intent);
                    finish();
                }
            }).show();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webview.canGoBack()){
                    webview.goBack();
                }
            }
        });

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inputLayout =  getLayoutInflater().inflate(R.layout.input_layout,null);
                final EditText editText = inputLayout.findViewById(R.id.edittext);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setCustomTitle(inputLayout).setMessage("上方填写霸气的标题").setNegativeButton("我已认定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setMyTitle(editText.getText().toString().trim());
                    }
                }).create();
                dialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl(HOME);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.reload();
            }
        });

        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }
        });
        webview.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.e("----should", ""+url);
                try {
                    if(!url.startsWith("http")){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                    view.loadUrl(url);
                    return true;
                }catch (Exception e){
//                    Log.e("----should--error", ""+e.getMessage());
                }
                return super.shouldOverrideUrlLoading(view,url);

            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//                Log.e("----Request", ""+url);
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Log.e("----shoLoading", ""+request.toString());
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                Log.e("----onReceivedError", ""+error);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
//                Log.e("----onReceivedError", "failingUrl:"+failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                Log.e("----onPageFinished", ""+url);

            }
        });
//        webview.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                Log.e("----onKey", "bbbb");
//
//                return false;
//            }
//        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowContentAccess(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.loadUrl(HOME);
    }

    private String getMyTitle(){
        return getSharedPreferences("myConfig",MODE_PRIVATE).getString("title",getString(R.string.app_name));
    }

    private void setMyTitle(String title){
        titleView.setText(title);
        SharedPreferences.Editor editor = getSharedPreferences("myConfig",MODE_PRIVATE).edit();
        editor.putString("title",title);
        editor.commit();
    }

    long mills = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(!webview.getUrl().equals(HOME) && webview.canGoBack()){
                webview.goBack();
                return true;
            }
            if(System.currentTimeMillis()-mills>800){
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                mills = System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
