package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Timer;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;

import java.util.ArrayList;
import java.util.List;

public class Rotation {
    public static List<Rotation> list = new ArrayList<>();
    public Urect Obj;
    public Transition_Type transition_type;
    public double FromRot, ToRot;
    public boolean Done = false;
    public int Time;
    public Timer AnimationTimer, WaitTimer;
    public int WaitTime;

    public Rotation(Urect obj, double fApha, double toAlpha, double time, Transition_Type transition_type, int Wait) {
        super();
        if (obj != null)
            obj.setRotationAnimation(this);
        Obj = obj;
        WaitTime = Wait;
        FromRot = fApha;
        ToRot = toAlpha;
        Time = (int) time;
        this.transition_type = transition_type;
        list.add(this);
        ManipulateTimers();
    }

    public void ManipulateTimers() {
        if (AnimationTimer != null)
            return;
        AnimationTimer = new Timer(Time, 0);
        WaitTimer = new Timer(WaitTime, 0);
        WaitTimer.start();
        AnimationTimer.OnTimerFinishCounting(t -> {
            Calc();
            Rotation.this.Done = true;
            t.Remove();
        });
        AnimationTimer.OnEveryStep((curentTime, t) -> Calc());
        WaitTimer.OnTimerFinishCounting(t -> {
            AnimationTimer.start();
            t.Remove();
        });
    }

    public void Calc() {
        Obj.setRotate(Transition.GetValue(transition_type, AnimationTimer.CurentTime, FromRot, ToRot, AnimationTimer.MaxTime));

    }

    public static void update() {

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).Done) {
                list.remove(i);
                i--;
            }
        }

    }
}
