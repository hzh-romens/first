package com.example.lanxumit.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * Created by Lanxumit on 2017/4/1.
 */

public class SecondActivity extends Activity {
    private LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        container = (LinearLayout) findViewById(R.id.container);
        ExampleView exampleView = new ExampleView(this);
        exampleView.invalidate();
        container.addView(exampleView);

    }
}
