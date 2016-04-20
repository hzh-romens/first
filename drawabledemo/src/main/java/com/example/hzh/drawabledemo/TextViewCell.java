package com.example.hzh.drawabledemo;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by HZH on 2016/1/6.
 */
public class TextViewCell extends FrameLayout {
    private TextView textView;

    public TextViewCell(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.list_item_layout, null);
        textView = (TextView) view.findViewById(R.id.tv);
        addView(view);
    }

    public void setValue(String value) {
        textView.setText(value);
    }
}
