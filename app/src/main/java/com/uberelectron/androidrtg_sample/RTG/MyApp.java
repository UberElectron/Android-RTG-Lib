package com.uberelectron.androidrtg_sample.RTG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.uberelectron.androidrtg.RTG_App;

public class MyApp implements RTG_App
{

    @Override
    public void Start()
    {
        MyData.getInstance().init();
    }

    @Override
    public void Render(Canvas c)
    {
        MyData data = MyData.getInstance();

        c.drawRect(data.rect1, data.mainPaint);
    }

    @Override
    public void Update(float dt)
    {

    }

    @Override
    public void onSurfaceChanged(int width, int height)
    {

    }

    @Override
    public void Stop()
    {
        Log.i("MyApp", "App quit");
    }
}
