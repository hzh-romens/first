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
        KaoyaSource kaoyaSource = new KaoyaSource();
        Mutil_Producer producer = new Mutil_Producer(kaoyaSource);
        Mutil_Consumer consumer = new Mutil_Consumer(kaoyaSource);

        //生产者线程
        Thread t0 = new Thread(producer);
        Thread t1 = new Thread(producer);
        //消费者线程

        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(consumer);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

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
            while (ticket > 0) {
                sale();
            }
        }

        public void sale() {
            synchronized (Object.class) {
                if (ticket > 0) {
                    Log.i("~~~~", Thread.currentThread().getName() + "正在卖第" + ticket-- + "票");
                } else {
                    Log.i("~~~~~", "票卖完了");
                }
            }
        }
    }

    public class KaoyaSource {
        private int maxCount = 100;
        private String name;
        private int count = 0;//烤鸭的初始数量
        //一个盘子只能装三只烤鸭，当盘子里有三只烤鸭的时候
        //private boolean flag = false;//判断是否有需要线程等待的标志

        //生产烤鸭
        public synchronized void product(String name) {

            while (count == 3) {
                //此时有烤鸭等待
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name + count;//设置烤鸭的名称
            count++;
            //maxCount--;
            System.out.println(Thread.currentThread().getName() + "...生产者..." + name);
            //flag = true;//有烤鸭后标志改变
            notifyAll();//通知消费线程可以消费了
        }

        //消费烤鸭
        public synchronized void consume(String name) {
            while (count == 0) {
                                                //如果没有烤鸭就等待
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //flag = false;
            //maxCount--;
            count--;
            this.name = name + count;
            System.out.println(Thread.currentThread().getName() + "...消费者........" + name);//消费烤鸭1
            notifyAll();//通知生成者生产烤鸭
        }
    }

    class Mutil_Producer implements Runnable {
        private KaoyaSource r;

        Mutil_Producer(KaoyaSource r) {
            this.r = r;
        }

        @Override
        public void run() {
           // while (r.getMaxCount()>=0) {
            for (int i = 0; i < 100; i++) {
                r.product("北京烤鸭"+i);
            }

            //}
        }
    }

    class Mutil_Consumer implements Runnable {
        private KaoyaSource r;

        Mutil_Consumer(KaoyaSource r) {
            this.r = r;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                r.consume("北京烤鸭"+i);
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
