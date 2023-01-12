package com.uberelectron.androidrtg;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;

public class RTG_Thread extends Thread
{
    public static final String TAG = "RTG_Thread";

    // Used to wait for the thread to start.
    private Object mStartLock = new Object();
    private boolean mReady = false;


    private volatile RTG_App app;
    private volatile RTG_Handler handler;

    private final SurfaceHolder holder;

    private final Class<? extends RTG_Handler> customHandlerClass;

    private Looper loop;


    public RTG_Thread(Class<? extends RTG_App> rtgApp, Class<? extends RTG_Handler> handler,
                      SurfaceHolder rtgSurfaceHolder)
    {
        holder = rtgSurfaceHolder;
        customHandlerClass = handler;

        //Instantiate the rtgApp.
        try
        {
            app = rtgApp.newInstance();
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error instantiating rtgApp");
        }

        //TODO: Wait until it's ready.
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

        synchronized (mStartLock) {
            mReady = true;
            mStartLock.notify();    // signal waitUntilReady()
        }

        //Start App.
        app.Start();

        //Update Surface Size so initial size can be got.
        Rect surfaceRect = holder.getSurfaceFrame();
        app.onSurfaceChanged(surfaceRect.width(), surfaceRect.height());

        //Loop!!!
        Looper.loop();

        synchronized (mStartLock) {
            mReady = false;
        }
        Log.i(TAG, "RTG_Thread Ended");
    }

    /**
     * Waits until the render thread is ready to receive messages.
     * Call from the RTG_Surface (UI thread).
     */
    public void waitUntilReady() {
        synchronized (mStartLock) {
            while (!mReady) {
                try {
                    mStartLock.wait();
                } catch (InterruptedException ie) { /* not expected */ }
            }
        }
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
