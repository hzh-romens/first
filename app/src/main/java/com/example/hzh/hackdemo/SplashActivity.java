package com.example.hzh.hackdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hzh.hackdemo.presenter.SplashPresenter;
import com.example.hzh.hackdemo.view.ISplashView;

/**
 * Created by HZH on 2015/11/30.
 */
public class SplashActivity extends AppCompatActivity implements ISplashView{
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private ISplashView iSplashView;
    private SplashPresenter mPresenter=new SplashPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mPresenter.setView(this);
        mTextView= (TextView) findViewById(R.id.splash_text);
        mProgressBar= (ProgressBar) findViewById(R.id.splash_progress_bar);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.didFinishLoading();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePreogress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoInetErrorMsg() {
        mTextView.setText("No internet");
    }

    @Override
    public void moveToMainView() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
