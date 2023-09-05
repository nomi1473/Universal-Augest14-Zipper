package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Timer;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;

import java.util.ArrayList;
import java.util.List;

public class Fade {
    public static List<Fade> list = new ArrayList<>();
    public Urect Obj;
    public Transition_Type transition_type;
    public double FromApha, ToAlpha;
    public boolean Done = false;
    public int Time;
    public Timer AnimationTimer, WaitTimer;
    public int WaitTime;

    public Fade(Urect obj, double fApha, double toAlpha, double time, Transition_Type transition_type) {
        super();
        if (Obj != null)
            Obj.setFadeAnnimation(this);

        Obj = obj;
        if (fApha > 255)
            fApha = 255;
        if (toAlpha < 0)
            toAlpha = 0;
        FromApha = fApha;
        ToAlpha = toAlpha;
        WaitTime = 0;
        Time = (int) time;
        this.transition_type = transition_type;
        list.add(this);
        ManipulateTimers();
    }

    public void ManipulateTimers() {
        if (Obj != null)
            Obj.setFadeAnnimation(this);
        if (AnimationTimer != null)
            return;
        AnimationTimer = new Timer(Time, 0);
        WaitTimer = new Timer(WaitTime, 0);
        WaitTimer.start();
        AnimationTimer.OnTimerFinishCounting(t -> {
            Calc();
            Fade.this.Done = true;
            t.Remove();
        });
        AnimationTimer.OnEveryStep((curentTime, t) -> Calc());
        WaitTimer.OnTimerFinishCounting(t -> {
            AnimationTimer.start();
            t.Remove();
        });
    }

    public void Calc() {
        Obj.setAlpha(Transition.GetValue(transition_type, AnimationTimer.CurentTime, FromApha, ToAlpha, AnimationTimer.MaxTime));
    }

    public static void update() {

        for (int d = 0; d < list.size(); d++) {
            Fade f = list.get(d);
            if (!f.Done) {
                list.remove(f);
                d--;
            }
        }

    }

    public void Remove() {

        if (AnimationTimer != null) {
            AnimationTimer.Remove();
            WaitTimer.Remove();
        }
        Done = true;
        list.remove(this);
    }
}
