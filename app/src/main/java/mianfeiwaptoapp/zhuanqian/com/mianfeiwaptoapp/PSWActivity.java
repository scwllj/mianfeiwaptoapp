package mianfeiwaptoapp.zhuanqian.com.mianfeiwaptoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

public class PSWActivity extends BaseActivity {

    Button confirm;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psw);
        confirm = findViewById(R.id.confirm);
        editText = findViewById(R.id.edittext);
        initAD();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String psw = editText.getText().toString();
                if(TextUtils.isEmpty(psw)){
                    return;
                }
                if(!Util.checkPSW(PSWActivity.this,psw)){
                    Toast.makeText(PSWActivity.this,"密码错误，无法强势进入",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(PSWActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void refreshAD() {
        ADTool.loadAD((AdView) findViewById(R.id.adView),ADTool.AD_BANNER);
        ADTool.loadAD((AdView) findViewById(R.id.adViewrectangle),ADTool.AD_BANNER_RECTANGLE);
    }

    private void initAD(){
        ADTool.loadAD((AdView) findViewById(R.id.adView),ADTool.AD_BANNER);
        ADTool.loadAD((AdView) findViewById(R.id.adViewrectangle),ADTool.AD_BANNER_RECTANGLE);
    }
}
