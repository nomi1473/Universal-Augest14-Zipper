package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends SurfaceView implements Runnable {

    public boolean IsFinished = false;

    public Drawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        drawListners = new ArrayList<>();
        myHolder = getHolder();
        myHolder.setFormat(PixelFormat.TRANSPARENT);
        this.setDrawingCacheEnabled(true);
    }

    public Drawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawListners = new ArrayList<>();
        myHolder = getHolder();
        myHolder.setFormat(PixelFormat.TRANSPARENT);
        this.setDrawingCacheEnabled(true);
    }

    public Drawer(Context context) {
        super(context);
        drawListners = new ArrayList<>();
        myHolder = getHolder();
        myHolder.setFormat(PixelFormat.TRANSPARENT);
        this.setDrawingCacheEnabled(true);
    }

    public List<DrawListner> drawListners;
    SurfaceHolder myHolder;
    public Thread myThread = null;
    public boolean isRunning;
    public static Canvas canvas;


    public void pause() {
        isRunning = false;
    }

    public void resume() {
        if (!isRunning) {
            isRunning = true;
            myThread = new Thread(this);
            myThread.start();
        }
    }


    @Override
    public void run() {
        while (isRunning && !IsFinished) {
            if (!myHolder.getSurface().isValid())
                continue;
            Draw();
        }

    }

    private void Draw() {
        canvas = myHolder.lockCanvas();
        UpdateDrawing();
        try {
            myHolder.unlockCanvasAndPost(canvas);
        } catch (Exception e) {
            Log.i("Canvas problem", "Canvas problem");
        }

    }

    //beautiful
    //--------------------- draw events trigers interfaces ...--------------------------------------
    public void UpdateDrawing() {
        //canvas.drawColor(0x00AAAAAA);
        canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
        for (DrawListner drs : drawListners)
            drs.OnDraw(canvas);
    }

    public void addOnDrawListner(DrawListner listner) {
        drawListners.add(listner);
    }

    public interface DrawListner {
        void OnDraw(Canvas canvas);
    }

    public void stopDrawing() {
        pause();
    }
}
