package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.layers;


import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.Deplace;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.GameAdapter;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Screen;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Uimage;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.UimagePart;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition_Type;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;

public class ZipperTouchListener {
    public static UnlockType unlocktype = UnlockType.Type3;
    public static Transition_Type transitiontype = Transition_Type.easeinOutbounce;
    static double LastY = 0;
    static double MaxSpikeRotation = 40;
    public static Uimage locker;
    static boolean ClickDown = false;
    static Deplace deplace;
    static double marzinMarge=(Media.ChainLeft.getWidth()/100)*18;

    public static void Inicial() {

        locker = new Uimage(0, 0, Media.zipper.getWidth(), Media.zipper.getHeight(), Media.zipper);
        GameAdapter.GetMainRect().AddChild(locker);
        locker.setLeft(Screen.Width / 2 - locker.Width() / 2-marzinMarge/2);
        locker.addOnClickDownListner((X, Y) -> {
            // TODO Auto-generated method stub
            ClickDown = true;
            if (deplace != null)
                deplace.remove();
        });
        locker.addOnClickUpListner((X, Y) -> {
            ClickDown = false;
            if (locker.getTop() > Screen.Height / 4 && locker.getTop() >= LastY)
                ZipperTouchListener.SllideDown();
            else {
                ZipperTouchListener.SllideUp();
            }
        });
        locker.addOnTouchMoveListner((Curentobj, X, Y) -> {
            if (ClickDown && Y > Curentobj.Height() / 2) {
                LastY = Curentobj.getTop();
                Curentobj.setTop(Y - Curentobj.Height() / 2);
            }
        });

        locker.OnUpdateListner(Curentobj -> {
            if (unlocktype == UnlockType.Type1)
                ZipperTouchListener.UpdateType1(Curentobj.getTop());
            else if (unlocktype == UnlockType.Type2)
                ZipperTouchListener.UpdateType2(Curentobj.getTop());
            else if (unlocktype == UnlockType.Type3)
                ZipperTouchListener.UpdateType3(Curentobj.getTop());
        });
    }

    public static int Duration = 550;

    public static void SllideUp() {
        deplace = new Deplace(locker, locker.getLeft(), 0, ConstantData.AnimationSpeed, Transition_Type.easeOutQuad, 0);
    }

    public static void SllideDown() {
        deplace = new Deplace(locker, locker.getLeft(), Screen.Height * 2, ConstantData.AnimationSpeed * 2, Transition_Type.easeOutQuad, 0);
    }

    public static void UpdateType3(double y) {
        ZipperView.UpdateWidgets(y);
        for (int i = 0; i < ZipperView.left.size(); i++) {
            UimagePart im = ZipperView.left.get(i);
            if (y > im.getTop()) {
                //im.setWidth((Screen.Width/2)-(y-im.getTop()));
                double from = Screen.Width / 2, to = ZipperView.space2, curenttime = y - im.getTop(), duration = Screen.Height / 2;
                double w = Transition.GetValue(transitiontype, curenttime, from, to, duration);
                if (curenttime > duration)
                    w = to;
                im.setWidth(w);
                im.ImageRect.setWidth((im.Width() / Screen.Width) * im.getImage().getWidth());
                Uimage chain = (Uimage) im.getChildrens().get(0);
                chain.setLeft(im.getRight() - chain.Width() + ZipperView.space + ZipperView.space2);
                if (chain.getLeft() > Screen.Width / 4) {
                    double r = Transition.GetValue(transitiontype, curenttime, 0, -MaxSpikeRotation, duration / 2);
                    chain.setRotate(r);
                } else if (curenttime < duration) {
                    double r = Transition.GetValue(transitiontype, curenttime - duration / 2, -MaxSpikeRotation, 0, duration / 2);
                    chain.setRotate(r);
                } else
                    chain.setRotate(0);

            } else {
                im.setWidth(Screen.Width / 2);
                Uimage chain = (Uimage) im.getChildrens().get(0);
                im.ImageRect.setWidth(im.getImage().getWidth() / 2);
                chain.setLeft(im.getRight() - chain.Width() + ZipperView.space + ZipperView.space2);
                chain.setRotate(0);
            }
        }

        for (int i = 0; i < ZipperView.right.size(); i++) {
            UimagePart im = ZipperView.right.get(i);
            Uimage chain = (Uimage) im.getChildrens().get(0);
            if (y > im.getTop()) {
                double from = Screen.Width / 2 + ZipperView.space2, to = Screen.Width, curenttime = y - im.getTop(), duration = Screen.Height / 2;

                double l = Transition.GetValue(transitiontype, curenttime, from, to, duration);
                if (curenttime > duration)
                    l = to;
                im.setLeft(l);
                //im.setWidth((Screen.Width)-im.Width()) ;
                //im.setWidth((Screen.Width/2)-(y-im.getTop()));
                im.ImageRect.setLeft((im.getLeft() / Screen.Width) * im.getImage().getWidth() - 1);


                if (im.GetCenterX() < (Screen.Width / 4) * 3) {
                    double r = Transition.GetValue(transitiontype, curenttime, 0, MaxSpikeRotation, duration / 2);
                    chain.setRotate(r);
                } else if (im.getLeft() < Screen.Width) {
                    double r = Transition.GetValue(transitiontype, curenttime - duration / 2, MaxSpikeRotation, 0, duration / 2);
                    chain.setRotate(r);
                } else
                    chain.setRotate(0);

            } else {
                im.setLeft(Screen.Width / 2 + ZipperView.space2);
                im.setWidth(Screen.Width / 2);
                //im.ImageRect.setLeft(im.getImage().getWidth()/2);
                im.ImageRect.setLeft((im.getLeft() / Screen.Width) * im.getImage().getWidth() - 1);
                chain.setRotate(0);
            }
        }
    }

    public static void UpdateType1(double y) {
        for (int i = 0; i < ZipperView.left.size(); i++) {
            UimagePart im = ZipperView.left.get(i);
            if (y > im.getTop()) {
                im.setLeft(im.getTop() - y - ZipperView.space2);

            } else {
                im.setLeft(-ZipperView.space2);
                im.skewX = 0;
            }
        }

        for (int i = 0; i < ZipperView.right.size(); i++) {
            UimagePart im = ZipperView.right.get(i);
            if (y > im.getTop()) {
                im.setLeft(Screen.Width / 2 + y - im.getTop() + ZipperView.space2);
            } else {
                im.setLeft(Screen.Width / 2 + ZipperView.space2);
            }
        }
    }

    public static void UpdateType2(double y) {

        double h = Screen.Height / ZipperView.left.size();
        for (int i = 0; i < ZipperView.left.size(); i++) {
            UimagePart im = ZipperView.left.get(i);
            if (y > im.getTop()) {
                im.setHeight(h - h * ((y - im.getTop()) / (Screen.Height - im.getTop())));
            } else {
                im.setHeight(h);

            }
        }
        for (int i = 0; i < ZipperView.right.size(); i++) {
            UimagePart im = ZipperView.right.get(i);
            if (y > im.getTop() + im.Height() / 2) {
                im.setHeight(h - h * ((y - im.getTop()) / (Screen.Height - im.getTop())));
            } else {
                im.setHeight(h);
            }
        }


    }

    public enum UnlockType {
        Type1, Type2, Type3
    }
}
