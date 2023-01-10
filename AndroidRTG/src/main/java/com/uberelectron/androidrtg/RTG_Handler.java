package com.uberelectron.androidrtg;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Default handler for RTG_Surface Events.
 * Should be overloaded for handling more events.
 */
public class RTG_Handler extends Handler
{
    protected static final String TAG = "RTG_Handler";

    protected RTG_Thread thread;

    /**
     * Default Constructor.
     * Used on RTG_Thread when instantiating a custom handler.
     * You have to call setThread when instantiated!!
     * (Until I find a better way...).
     */
    public RTG_Handler() {}

    public RTG_Handler(RTG_Thread ownerThread)
    {
        thread = ownerThread;
    }


    /**
     * Set the owner thread to pass events to.
     * When instantiated by default constructor.
     * @param ownerThread
     */
    public void setThread(RTG_Thread ownerThread)
    {
        if(thread == null) { this.thread = ownerThread; }
    }


    //Messages.

    protected static final int MSG_SURFACE_CREATED = 0;
    protected static final int MSG_SURFACE_CHANGED = 1;
    protected static final int MSG_SHUTDOWN = 2;
    protected static final int MSG_DO_FRAME = 3;

    public void sendSurfaceCreated()
    {
        sendMessage(obtainMessage(MSG_SURFACE_CREATED));
    }

    public void sendSurfaceChanged(int width, int height)
    {
        sendMessage(obtainMessage(MSG_SURFACE_CHANGED, width, height));
    }

    public void sendShutdown()
    {
        sendMessage(obtainMessage(MSG_SHUTDOWN));
    }

    public void sendDoFrame(long frameTimeNanos)
    {
        sendMessage(obtainMessage(MSG_DO_FRAME,
                (int) (frameTimeNanos >> 32), (int) frameTimeNanos));
    }


    /**
     * Override this method to handle custom events!
     * Remenber to call super.sendMessageAtTime(msg, uptimeMillis); so default events are handled as well.
     * @return True if msg was handled (placed on the message queue). False if not "handled".
     */
    @Override
    public boolean sendMessageAtTime(@NonNull Message msg, long uptimeMillis)
    {
        if(thread == null)
        {
            Log.e(TAG, "Null reference to RTG_Thread!!");
            return false;
        }

        switch (msg.what)
        {
            case MSG_SURFACE_CREATED:
                //TODO: ¿Algo cuando se crea la surface?
                break;
            case MSG_SURFACE_CHANGED:
                thread.onSurfaceChange(msg.arg1, msg.arg2);
                break;
            case MSG_SHUTDOWN:
                thread.shutdown();
                break;
            case MSG_DO_FRAME:
                long timestamp = (((long) msg.arg1) << 32) |
                        (((long) msg.arg2) & 0xffffffffL);
                thread.doFrame(timestamp);
                break;
        }

        //FIXME: Esto considera que estamos gestionando, pero no estoy tan seguro de ello...
        return true;
        //return super.sendMessageAtTime(msg, uptimeMillis);
    }
}
