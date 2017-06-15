package com.example.hzh.observedemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.utils.SharedPerfrenceUtils;
import com.example.hzh.observedemo.utils.UIOpenhelper;

/**
 * Created by pc on 2016/4/28.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sureBtn = (TextView) findViewById(R.id.sure);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure:
                SharedPerfrenceUtils.getInstance().saveLaunched("login", true);
                UIOpenhelper.ToOther(this, MainActivity.class);
                break;
        }
    }
}
