package com.uberelectron.androidrtg_sample.RTG;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyData
{
    //SINGLETON!
    private static MyData instance;

    public static MyData getInstance()
    {
        //Singleton with double-checked locking (DCL).

        MyData tmpInstance = instance;
        if(tmpInstance != null) { return tmpInstance; }

        synchronized (MyData.class)
        {
            if(instance == null)
            {
                instance = new MyData();
            }
            return instance;
        }
    }

    // END SINGLETON.

    //Constructor should be private for singleton.
    private MyData() {}


    public Rect rect1;
    public Paint mainPaint;

    /**
     * Create all starting data.
     */
    public void init()
    {
        rect1 = new Rect(50, 50, 150, 200);

        mainPaint = new Paint();
        mainPaint.setColor(Color.CYAN);
    }

}
