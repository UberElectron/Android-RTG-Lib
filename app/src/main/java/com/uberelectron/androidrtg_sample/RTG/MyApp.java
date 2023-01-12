package com.uberelectron.androidrtg_sample.RTG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    public void Update()
    {

    }

    @Override
    public void onSurfaceChanged(int width, int height)
    {

    }

    @Override
    public void Stop()
    {

    }
}
