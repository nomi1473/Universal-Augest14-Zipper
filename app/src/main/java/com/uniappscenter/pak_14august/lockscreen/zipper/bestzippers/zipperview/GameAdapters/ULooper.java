package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters;

import android.util.Log;

public class ULooper implements Runnable {

    public static Thread t;
    public Thread Gamethread;
    public static int sleep = 1;
    public boolean isRunning;

    public void Resume() {
        isRunning = true;
        Gamethread = new Thread(this);
        Gamethread.start();
    }

    public void Pause() {
        isRunning = false;
    }

    public ULooper() {
        isRunning = true;
        //Resume();
    }

    @Override
    public void run() {

        while (isRunning) {
            Update();
            try {
                Gamethread.sleep(sleep);
            } catch (InterruptedException e) {
                Log.i("Looper Error", "Looper Error :" + e.getMessage());
            }
            ct++;

        }

        Log.i("Looper run out", "Looper run out");

    }

    public static int ct = 0;

    public void Update() {
        GameAdapter.Update();
    }
}
