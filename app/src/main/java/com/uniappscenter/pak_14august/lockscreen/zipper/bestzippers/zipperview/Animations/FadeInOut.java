package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;

import java.util.ArrayList;
import java.util.List;

public class FadeInOut {
    public static List<FadeInOut> list = new ArrayList<>();
    public double Time, CurentTime, FlashNumber, CurentFlash, FA, TA;
    Transition_Type tr;
    Urect Obj;

    public FadeInOut(int FlashNumber, Urect obj, double fApha, double toAlpha, double time, Transition_Type transition_type) {
        super();
        this.Time = time;
        CurentTime = 0;
        Obj = obj;
        this.FlashNumber = FlashNumber;
        CurentFlash = 0;
        list.add(this);
        tr = transition_type;
        FA = fApha;
        TA = toAlpha;
        new Fade(Obj, FA, TA, Time, tr);
    }

    public boolean Calc() {
        if (CurentTime == Time) {
            if (FlashNumber != -1) {
                if (CurentFlash < FlashNumber) {
                    CurentFlash++;
                    CurentTime = 0;
                    double f = FA;
                    FA = TA;
                    TA = f;
                    new Fade(Obj, FA, TA, Time, tr);
                } else {
                    return false;
                }
            } else {
                double f = FA;
                FA = TA;
                TA = f;
                CurentTime = 0;
                new Fade(Obj, FA, TA, Time, tr);
            }

        }
        CurentTime++;
        return true;
    }

    public static void update() {
        try {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).Calc()) {
                    list.remove(i);
                    i--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}