package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations;



import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Timer;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;

import java.util.ArrayList;
import java.util.List;

public class Deplace {
    public static List<Deplace> list = new ArrayList<>();
    public Transition_Type transition_type;
    public Urect Obj;
    public double Fx, Fy, Tx, Ty;
    public int Time, CurentTime, Wait;
    public Timer AnimationTimer, WaitTimer;
    public boolean JustX = false, JustY = false, Done = false;

    public void ManipulateTimers() {

        if (Obj != null)
            Obj.setDeplaceAnnimation(this);
        if (AnimationTimer != null)
            return;
        AnimationTimer = new Timer(Time, 0);
        WaitTimer = new Timer(Wait, 0);
        WaitTimer.start();
        AnimationTimer.OnTimerFinishCounting(t -> {
            Calc();
            Deplace.this.Done = true;
            t.Remove();
        });
        AnimationTimer.OnEveryStep((curentTime, t) -> Calc());
        WaitTimer.OnTimerFinishCounting(t -> {
            AnimationTimer.start();
            t.Remove();
        });
	}

    public Deplace(Urect obj, double tx, double ty, double time, Transition_Type transition_type, double wait) {
        super();
        if (Obj != null)
            Obj.setDeplaceAnnimation(this);

        Obj = obj;
        Fx = obj.getRelativeLeft();
        Fy = obj.getRelativeTop();
        Tx = tx;
        Ty = ty;
        Time = (int) time;
        CurentTime = 0;
        this.transition_type = transition_type;
        list.add(this);
        Wait = (int) wait;
        ManipulateTimers();
    }

    public void Calc() {
        if (!JustY && !JustX) {
            Obj.setLeft(Transition.GetValue(transition_type, AnimationTimer.CurentTime, Fx, Tx, AnimationTimer.MaxTime));
            Obj.setTop(Transition.GetValue(transition_type, AnimationTimer.CurentTime, Fy, Ty, AnimationTimer.MaxTime));
        } else if (JustX)
            Obj.setLeft(Transition.GetValue(transition_type, AnimationTimer.CurentTime, Fx, Tx, AnimationTimer.MaxTime));
        else
            Obj.setTop(Transition.GetValue(transition_type, AnimationTimer.CurentTime, Fy, Ty, AnimationTimer.MaxTime));
    }

    public static void update() {

        for (int i = 0; i < list.size(); i++) {
            try {
                if (!list.get(i).Done) {
                    list.remove(i);
                    i--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        if (AnimationTimer != null) {
            AnimationTimer.Remove();
            WaitTimer.Remove();
        }
        Done = true;
        list.remove(this);

    }
}
