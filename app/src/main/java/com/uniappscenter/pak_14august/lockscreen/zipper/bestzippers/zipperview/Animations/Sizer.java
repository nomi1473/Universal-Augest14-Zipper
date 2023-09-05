package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Timer;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;

import java.util.ArrayList;
import java.util.List;

public class Sizer {
    public static List<Sizer> list = new ArrayList<>();
    public Transition_Type transition_type;
    public Urect Obj;
    public double Fw, Fh, Tw, Th, Time, CurentTime;
    public Timer waitTimer;

    public Sizer(Urect obj, double fw, double fh, double tw, double th,
                 double time, Transition_Type transition_type, int wait) {
        super();
        //DeleteIfExist(obj);
        waitTimer = new Timer(wait, 0);
        waitTimer.start();
        Obj = obj;
        Fw = fw;
        Fh = fh;
        Tw = tw;
        Th = th;
        Time = time;
        CurentTime = 0;
        this.transition_type = transition_type;
        list.add(this);


        Obj.setSizerAnnimation(this);
    }

    public boolean Calc() {
        if (waitTimer.IsFinished()) {
            Obj.setWidth(Transition.GetValue(transition_type, CurentTime, Fw, Tw, Time));
            Obj.setHeight(Transition.GetValue(transition_type, CurentTime, Fh, Th, Time));
            if (CurentTime == Time)
                return false;
            CurentTime++;
        }
        return true;
    }

    public static void update() {
        for (int i = 0; i < list.size(); i++) {
            try {
                if (!list.get(i).Calc()) {
                    list.get(i).waitTimer.Remove();
                    list.remove(i);
                    i--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
