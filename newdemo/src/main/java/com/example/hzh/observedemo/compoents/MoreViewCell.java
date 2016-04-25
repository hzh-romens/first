package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hzh.observedemo.R;

/**
 * Created by HZH on 2016/4/18.
 */
public class MoreViewCell extends FrameLayout {
    private Button more;
    private ButtonClickListener mListener;

    public MoreViewCell(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_more, null);
        more = (Button) view.findViewById(R.id.more);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ButtonClick();
            }
        });
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.mListener = listener;
    }

    public interface ButtonClickListener {
        void ButtonClick();
    }
}
