package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.layers.ZipperTouchListener;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.layers.ZipperView;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.ZipperLockService;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.AnimationAdapter;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.ULabel;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;


public class GameAdapter {

    public static Urect Scene, Background;
    public static Drawer drawer;
    public static ULooper looper;
    public static ULabel ObjCount;
    public static boolean Inicialed = false;

    public static Urect GetMainRect() {
        return Background;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void StartGame() {
        Ispaused = true;
        closing = false;
        readyToClose = false;
        //if(Inicialed)
        //return false;
        Inicialed = true;
        Screen.Inicialize();
        Media.inicial();
        Background = new Urect(0, 0, Screen.Width, Screen.Height, Color.TRANSPARENT);
        Scene = new Urect(0, 0, Screen.Width, Screen.Height, Color.TRANSPARENT);
        Scene.AddChild(Background);
        Scene.setColor(Color.TRANSPARENT);
        Scene.setAlpha(255);

        ObjCount = new ULabel(0, 0, Screen.Width, Screen.Height, "0");
        ObjCount.SetTextSize(Screen.Width / 10);
        ObjCount.setColor(Color.RED);
        ObjCount.OnUpdateListner(Curentobj -> {

        });


        if (drawer == null) {
            drawer = new Drawer(ConstantData.globalContext);
            drawer.setSoundEffectsEnabled(false);
            looper = new ULooper();
            RelativeLayout l = ZipperLockService.mOverlay.findViewById(R.id.mainLayout);
            //RelativeLayout l = SimpleLockScreen.mOverlay.findViewById(R.id.mainLayout);
            l.addView(GameAdapter.drawer);
        }


        ZipperView.Inicial();
        ZipperTouchListener.Inicial();


        drawer.addOnDrawListner(GameAdapter::Draw);
        Background.addOnTouchMoveListner((Curentobj, X, Y) -> {
        });
        drawer.setOnClickListener(v -> {
            Urect.CheckRectsClickUp();
            //closeThatShet();
        });
        drawer.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //Urect.CheckRectsClickDown(event.getX(), event.getY(), true);
                GetMainRect().checkClickDown(event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                Urect.CheckRectTouchMove(event.getX(), event.getY());
            }
            return false;
        });
        //drawer.resume();

        Background.addOnClickDownListner((X, Y) -> {

        });
    }

    public static void Pause() {
        if (Ispaused) {
            return;
        }
        if (drawer == null) {
            return;
        } else
            drawer.pause();
        if (looper != null)
            looper.Pause();
        looper = null;
        Ispaused = true;
    }

    public static void Resume() {
        if (!Ispaused) {
            return;
        }

        if (drawer == null) {
            return;
        }
        Ispaused = false;
        drawer.resume();
        looper = new ULooper();
        looper.Resume();
    }

    public static boolean Ispaused = true;
    static boolean closing = false;

    public static void Update() {
        try {
            AnimationAdapter.Update();
            Timer.Update();
            GetMainRect().CheckObjUpdates();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!closing && ZipperTouchListener.locker.getTop() >= Screen.Height * 1.7) {
            closing = true;
            closeThatShet();
        }

    }

    static void closeThatShet() {

        Timer t = new Timer(100, 0);
        t.start();
        t.OnTimerFinishCounting(t1 -> {
            // TODO Auto-generated method stub
            Handler mainHandler = new Handler(ConstantData.globalContext.getMainLooper());
            Runnable myRunnable = () -> {
                ZipperLockService.Close();
                Inicialed = false;

            };
            mainHandler.post(myRunnable);
        });


    }

    static boolean readyToClose = false;

    public static void Draw(Canvas canvas) {
        Scene.Draw(canvas);
    }

    public static void close() {
        Inicialed = false;
        if (looper != null) {
            drawer.pause();
            drawer.stopDrawing();
            drawer.IsFinished = true;
            drawer = null;
        }
        if (looper != null)
            looper.Pause();
        GetMainRect().clearChilds();
        Timer.Clear();
        GetMainRect().Delete();
        Media.Clear();
    }
}
