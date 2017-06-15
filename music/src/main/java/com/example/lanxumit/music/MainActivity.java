package com.example.lanxumit.music;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    private TextView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        start = (TextView) findViewById(R.id.start);
//        MyThread myThread1=new MyThread("一");
//        MyThread myThread2=new MyThread("二");
//        MyThread myThread3=new MyThread("三");
//        myThread1.start();
//        myThread2.start();
//        myThread3.start();

        //Thread thread=new Thread(MyRunable);
        MyRunable myRunable = new MyRunable();
        MyThread myThread2 = new MyThread("二");
        MyThread myThread3 = new MyThread("三");

        new Thread(myRunable, "线程一").start();
        new Thread(myRunable, "线程二").start();
        new Thread(myRunable, "线程三").start();
        DexClassLoader dexClassLoader=new DexClassLoader()

    }

    public class MyThread extends Thread {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            super.run();

            for (int i = 0; i < 10; i++) {
                Log.i("线程" + name, "------" + i);
            }

        }
    }

    public class MyRunable implements Runnable {
        int ticket = 20;

        @Override
        public void run() {
            while (ticket>0){
                sale();
            }
        }

        public void sale() {
            synchronized (Object.class) {
                if (ticket > 0) {
                    Log.i("~~~~", Thread.currentThread().getName() + "正在卖第" + ticket-- + "票");
                } else {
                    Log.i("~~~~~","票卖完了");
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
