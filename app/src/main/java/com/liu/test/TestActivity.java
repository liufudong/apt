package com.liu.test;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.liu.annotation.MyAnnotation;

import java.io.File;

//2020-07-17 10:33:19.227 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.242 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.246 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.247 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.285 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.286 11084-11084/com.liu.test I/System.out: =====MyTextView.onMeasure==
//2020-07-17 10:33:19.288 11084-11084/com.liu.test I/System.out: ====MyTextView.=onLayout==
@MyAnnotation(text="123")
public class TestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        final TextView mTextView = findViewById(R.id.mTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InjectActivity.inject(TestActivity.this);//调用build生成的类
            }
        });
    }

}
