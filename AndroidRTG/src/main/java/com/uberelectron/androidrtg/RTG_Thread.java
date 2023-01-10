package com.uberelectron.androidrtg;

import android.graphics.Canvas;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;

public class RTG_Thread extends Thread
{
    public static final String TAG = "RTG_Thread";

    private volatile RTG_App app;
    private volatile RTG_Handler handler;

    private volatile SurfaceHolder holder;

    private volatile Class<? extends RTG_Handler> customHandlerClass;

    private Looper loop;


    public RTG_Thread(Class<? extends RTG_App> rtgApp, Class<? extends RTG_Handler> handler,
                      SurfaceHolder rtgSurfaceHolder)
    {
        //Instantiate the rtgApp.
        try
        {
            app = rtgApp.newInstance();
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error instantiating rtgApp");
            return;
        }

        customHandlerClass = handler;

        holder = rtgSurfaceHolder;

    }


    @Override
    public void run()
    {
        Looper.prepare();
        loop = Looper.myLooper();

        //Create Handler.
        if(customHandlerClass == null)
        {
            this.handler = new RTG_Handler(this);
        } else
        {
            try
            {
                this.handler = customHandlerClass.newInstance();
                handler.setThread(this);
            }
            catch (Exception e)
            {
                Log.e(TAG, "Error instantiating custom Handler");
                return;
            }
        }

        //Start App.
        app.Start();

        //Loop!!!
        Looper.loop();


        Log.i(TAG, "RTG_Thread Ended");
    }


    public void shutdown()
    {
        Log.i(TAG, "Quitting RTG_Thread...");

        //Stop App
        app.Stop();

        loop.quit();
    }


    public void doFrame(long timeNanos)
    {
        Log.i(TAG, "Frame!!!");

        //TODO: Add time limiter.

        app.Update();

        Canvas c = holder.lockCanvas();
        c.drawRGB(255, 255, 255);

        app.Render(c);

        holder.unlockCanvasAndPost(c);
    }


    public void onSurfaceChange(int newWidth, int newHeight)
    {
        app.onSurfaceChanged(newWidth, newHeight);
    }


    /////////////////////////

    public RTG_Handler getHandler()
    {
        return handler;
    }

}
