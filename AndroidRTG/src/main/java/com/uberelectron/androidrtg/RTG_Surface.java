package com.uberelectron.androidrtg;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class RTG_Surface extends SurfaceView implements SurfaceHolder.Callback, Choreographer.FrameCallback
{
    private static final String TAG = RTG_Surface.TAG;

    private Class<? extends RTG_App> rtgAppClass;
    private Class<? extends RTG_Handler> rtgHandlerClass;

    private RTG_Thread thread;

    public RTG_Surface(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        getHolder().addCallback(this);
    }

    public void setApp(Class<? extends RTG_App> app)
    {
        rtgAppClass = app;
    }

    public void setHandlerClass(Class<? extends RTG_Handler> handler)
    {
        rtgHandlerClass = handler;
    }


    public RTG_Thread getThread() { return thread; }
    public RTG_Handler getThreadHandler()
    {
        if(thread == null) { return null; }
        return thread.getHandler();
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder)
    {
        //Init RTG_Thread if not started yet.
        if(thread != null) { return; }

        try
        {
            thread = new RTG_Thread(rtgAppClass, rtgHandlerClass, holder);
            thread.setName("RTG_Thread");
            thread.start();
            thread.waitUntilReady();

            //Send SurfaceCreated event to thread.
            RTG_Handler handler = thread.getHandler();
            if(handler != null)
            {
                handler.sendSurfaceCreated();
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error initiating the RTG_Thread!");
            return;
        }

        //Start draw events.
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height)
    {
        //Send surfaceChanged event to thread.
        if(thread == null) { return; }
        RTG_Handler handler = thread.getHandler();
        if(handler != null)
        {
            handler.sendSurfaceChanged(width, height);
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder)
    {
        Log.i(TAG, "Destroying RTG_App surface.");

        //Stop thread and callback.
        if(thread == null) { return; }
        RTG_Handler handler = thread.getHandler();
        if(handler != null)
        {
            handler.sendShutdown();
            while(true)
            {
                try
                {
                    thread.join();
                    Log.i(TAG, "surfaceDestroyed complete");
                    thread = null;
                    return;
                } catch (Exception ie)
                {
                    // Error stopping thread.
                    ie.printStackTrace();
                }
            }
        }
    }



    @Override
    public void doFrame(long frameTimeNanos)
    {
        if(thread == null) { return; }
        RTG_Handler handler = thread.getHandler();
        if(handler != null)
        {
            Choreographer.getInstance().postFrameCallback(this);
            handler.sendDoFrame(frameTimeNanos);
        }
    }
}
