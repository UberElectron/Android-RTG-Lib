package com.uberelectron.androidrtg_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uberelectron.androidrtg.RTG_Surface;
import com.uberelectron.androidrtg_sample.RTG.MyApp;
import com.uberelectron.androidrtg_sample.RTG.MyHandler;

public class ActivityExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        RTG_Surface mySurface = findViewById(R.id.mySurface);
        mySurface.setApp(MyApp.class);
        mySurface.setHandlerClass(MyHandler.class);

        mySurface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyHandler h = (MyHandler) mySurface.getThread().getHandler();
                h.sendTouch();
                return true;
            }
        });

        Button btBack = findViewById(R.id.btEx_Back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView titleText = findViewById(R.id.ActSampleTitleText);
        titleText.setText(R.string.exActTitle);
    }
}