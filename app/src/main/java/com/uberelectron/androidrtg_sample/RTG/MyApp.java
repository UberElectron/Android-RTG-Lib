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
        //NOT RECOMMENDED, Save Paint somewhere else!
        Paint p = new Paint();
        p.setColor(Color.CYAN);

        c.drawRect(MyData.getInstance().rect1, p);
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
