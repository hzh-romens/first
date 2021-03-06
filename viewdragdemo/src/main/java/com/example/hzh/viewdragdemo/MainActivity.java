package com.example.hzh.viewdragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TestButton testButton;
    protected static final String TAG = "MyButton";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton = (TestButton) findViewById(R.id.test);
        testButton.setOnTouchListener(new View.OnTouchListener()
               {
                   @Override
                     public boolean onTouch(View v, MotionEvent event)
                       {
                               int action = event.getAction();

                             switch (action)
                               {
                               case MotionEvent.ACTION_DOWN:
                                          Log.e(TAG, "onTouch ACTION_DOWN");
                                       break;
                                   case MotionEvent.ACTION_MOVE:
                                         Log.e(TAG, "onTouch ACTION_MOVE");
                                            break;
                               case MotionEvent.ACTION_UP:
                                          Log.e(TAG, "onTouch ACTION_UP");
                                            break;
                             default:
                                   break;
                                  }

                               return false;
                           }
                    });
          }

}
