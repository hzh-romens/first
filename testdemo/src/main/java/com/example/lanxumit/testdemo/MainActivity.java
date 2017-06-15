package com.example.lanxumit.testdemo;

import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.*;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView start;
    private SimpleDraweeView imageView1, imageView2, imageView3, imageView4, imageView5;
    String imageUrls[] = {"http://h5.86.cc/walls/20140930/mid_493efdd0f41eee8.jpg",
            "http://img.news.d.cn/UE/net/UEUpload/6357315944959662505177784.jpg",
            "http://pic.fjndwb.com/2017/0331/201703314.jpg",
            "http://f.hiphotos.baidu.com/zhidao/pic/item/5243fbf2b2119313dd7f38f963380cd791238dbf.jpg",
            "http://bcs.91.com/rbpiczy/Wallpaper/2015/1/9/d4b1ade76d3549bdbd94937201a32c0d-9.jpg"
    };
    List<SimpleDraweeView> imageList = new ArrayList<SimpleDraweeView>();
    private TextView text1, text2, text3, text4, text5;
    List<TextView> textViewList = new ArrayList<TextView>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    text1.setText("线程1");
                    break;
                case 2:
                    text2.setText("线程2");
                    break;
                case 3:
                    text3.setText("线程3");
                    break;
                case 4:
                    text4.setText("线程4");
                    break;
                case 5:
                    text5.setText("线程5");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        ObjectAnimator
        setContentView(R.layout.activity_main);
        SerialExecutorUtils serialExecutorUtils = new SerialExecutorUtils();
        Executors.
        start = (TextView) findViewById(R.id.start);
        text1 = (TextView) findViewById(R.id.text1);
        textViewList.add(text1);
        text2 = (TextView) findViewById(R.id.text2);
        textViewList.add(text2);
        text3 = (TextView) findViewById(R.id.text3);
        textViewList.add(text3);
        text4 = (TextView) findViewById(R.id.text4);
        textViewList.add(text4);
        text5 = (TextView) findViewById(R.id.text5);
        textViewList.add(text5);
//        imageView1 = (SimpleDraweeView) findViewById(R.id.image1);
//        imageList.add(imageView1);
//        imageView2 = (SimpleDraweeView) findViewById(R.id.image2);
//        imageList.add(imageView2);
//        imageView3 = (SimpleDraweeView) findViewById(R.id.image3);
//        imageList.add(imageView3);
//        imageView4 = (SimpleDraweeView) findViewById(R.id.image4);
//        imageList.add(imageView4);
//        imageView5 = (SimpleDraweeView) findViewById(R.id.image5);
//        imageList.add(imageView5);
        initRun();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(i * 1000);
                        //SerialExecutorUtils.SERIAL_EXECUTOR.execute(runnableList.get(i));
                        runnableList.get(i).run();
                        count = i + 1;
                        countDownLatch.countDown();
                    }
                    countDownLatch.await();
                    Log.i("-------", count + "测试");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int count;
    private CountDownLatch countDownLatch;

    public void initRun() {
        runnableList = new ArrayList<Runnable>();
        for (int i = 0; i < 5; i++) {
            runnableList.add(runnable);
        }

        countDownLatch = new CountDownLatch(runnableList.size());
    }

    private List<Runnable> runnableList;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("-------", "线程" + count);
            //handler.sendEmptyMessage(count);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("线程里面开线程", "--");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .url("https://www.baidu.com/")
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("e-----", e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                String s = response.body().toString();
                                Log.i("e-----", s);
                            } catch (Exception e) {
                                Log.i("e-----", e.toString());
                            } finally {
                                response.body().close();
                            }

                        }
                    });
                    //deliveryResult(callback, request);
                }
            }).start();
        }
    };
}
