package com.uberelectron.androidrtg;

import android.graphics.Canvas;

/**
 * Interface for defining RTG Apps.
 * It may not contain data, just logic.
 */
public interface RTG_App
{
    /**
     * To be executed by the RTG_Thread when it's created.
     */
    public void Start();

    /**
     * To be executed by the RTG_Renderer every frame.
     * @param c Canvas to draw on.
     */
    public void Render(Canvas c);

    /**
     * To be executed by the RTG_Thread every frame before Render.
     */
    public void Update();

    /**
     * Called when RTG_Surface changed (screen rotated, resolution changed, ...)
     * Called on surface creation (can be considered a "change").
     * Used for adjusting rendering to new resolution.
     * @param width new surface width.
     * @param height new surface height.
     */
    public void onSurfaceChanged(int width, int height);

    /**
     * Executed when the RTG_Thread ends.
     */
    public void Stop();
}
