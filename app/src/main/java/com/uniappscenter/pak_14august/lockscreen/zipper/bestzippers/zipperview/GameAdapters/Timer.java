package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters;

import java.util.ArrayList;
import java.util.List;


public class Timer {
    List<TimerFinishListner> TimerFinishListner = new ArrayList<>();
    List<TimerStepListner> TimerSteps = new ArrayList<>();


    public interface TimerFinishListner {
        void DoWork(Timer t);
    }

    public interface TimerStepListner {
        void DoWork(int curentTime, Timer t);
    }

    public void OnTimerFinishCounting(TimerFinishListner toAdd) {
        TimerFinishListner.add(toAdd);
        //list.remove(this);
    }

    public void OnEveryStep(TimerStepListner toAdd) {
        TimerSteps.add(toAdd);
    }


    public int MaxTime, CurentTime;
    public boolean Pause = true, Done = false;// done = this timer sould be remouved
    public boolean AlreadyFinished = false;
    public static List<Timer> list = new ArrayList<>();

    public Timer(int maxTime, int curentTime) {
        super();
        MaxTime = maxTime;
        CurentTime = curentTime;
        list.add(this);
    }

    public boolean IsFinished() {
        return CurentTime >= MaxTime || Done;
    }

    public boolean isPaused() {
        return Pause;
    }

    public boolean update() {
        if (!IsFinished()) {
            if (!isPaused()) {
                CurentTime++;
                for (TimerStepListner hl : TimerSteps)
                    hl.DoWork(CurentTime, this);
            }
            return false;
        } else {
            if (!AlreadyFinished) {
                AlreadyFinished = true;
                for (TimerFinishListner hl : TimerFinishListner)
                    hl.DoWork(this);
            }
        }
        return true;
    }

    public static void Update() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).Done) {
                list.remove(i);
                i--;
            } else
                list.get(i).update();
        }
    }

    public void start() {
        Pause = false;
        if (!list.contains(this))
            list.add(this);
    }

    public void Remove() {
        Done = true;
    }

    public static void Clear() {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).Delete();
            }
            list.clear();
        }
    }

    private void Delete() {
        TimerFinishListner.clear();
        TimerSteps.clear();
    }
}
