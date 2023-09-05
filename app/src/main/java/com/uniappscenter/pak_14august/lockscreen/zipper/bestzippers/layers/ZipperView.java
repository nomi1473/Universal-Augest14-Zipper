package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.layers;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Paint.Align;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.GameAdapter;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Screen;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.ULabel;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Uimage;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.UimagePart;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes.Urect;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition.Transition;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ZipperView {

    public static int count = 30;
    public static List<UimagePart> left, right;
    public static double space, space2;

    public static void Inicial() {
        space2 = Screen.Width / count;
        double space = Screen.Width / (count * 3);
        left = new ArrayList<>();
        right = new ArrayList<>();
        Bitmap bgImg = Media.SelectedBg;
        double width = Screen.Width / 2;
        double height = Screen.Height / count;
        double iwidth = bgImg.getWidth() / 2;
        double iheight = bgImg.getHeight() / count;
        double marzinMarge=(Media.ChainLeft.getWidth()/100)*18;
//20%
        UimagePart prt;
        for (int i = 0; i < count; i++) {
            Urect imgPart = new Urect(0, i * iheight, iwidth, iheight);
            prt = new UimagePart(-space, i * height, width, height, imgPart, Media.SelectedBg);
            left.add(prt);
            GameAdapter.GetMainRect().AddChild(prt);
            Uimage chain = new Uimage(0, 0, height * 2, height, Media.ChainLeft);
            chain.setLeft(prt.Width() - chain.Width() + space + space2+marzinMarge);
            chain.setTop(5);
            //GameAdapter.GetMainRect().AddChild(chain);
            prt.AddChild(chain);
        }
        for (int i = 0; i < count + 1; i++) {
            Urect imgPart = new Urect(iwidth, i * iheight - iheight / 2, iwidth, iheight);
            prt = new UimagePart(width + space/9, i * height - height / 2, width, height, imgPart, Media.SelectedBg);
            right.add(prt);
            GameAdapter.GetMainRect().AddChild(prt);


            Uimage chain = new Uimage(0, 0, height * 2, height, Media.ChainRight);
            chain.setLeft(-space - space2-marzinMarge);
            chain.setTop(5);
            //GameAdapter.GetMainRect().AddChild(chain);
            prt.AddChild(chain);
        }
        CreateWidgets();
    }

    static Calendar c;
    public static Urect TimeHolder;
    public static ULabel HoursMinutes, Date;
    public static SimpleDateFormat curFormater;
    public static Uimage BateryCover;
    static double baterryWidth;

    @SuppressLint("SimpleDateFormat")
    private static void CreateWidgets() {
        curFormater = new SimpleDateFormat("dd/MM/yyyy");

        TimeHolder = new Urect(Screen.Width / 100, Screen.Height - Screen.Width / 3, 0, 0);
        HoursMinutes = new ULabel(Screen.Width / 50, 0, 0, Screen.Width / 5, "");
        Date = new ULabel(Screen.Width / 36, Screen.Width / 10, 0, Screen.Width / 5, "");

        baterryWidth = Screen.Width / 2 - 130;
        BateryCover = new Uimage(0, 0, baterryWidth, Screen.Width / 4+10, Media.timeBgCover);
        TimeHolder.AddChild(BateryCover);


        HoursMinutes.SetTextSize(Screen.Width / 7);
        HoursMinutes.setColor(ConstantData.FontColor);
        HoursMinutes.setTextAlign(Align.LEFT);
        HoursMinutes.setFont(Media.font1);

        Date.SetTextSize(Screen.Width / 16);
        Date.setColor(ConstantData.FontColor);
        Date.setTextAlign(Align.LEFT);
        Date.setFont(Media.font1);

        TimeHolder.AddChild(HoursMinutes);
        TimeHolder.AddChild(Date);

        GameAdapter.GetMainRect().AddChild(TimeHolder);

        HoursMinutes.OnUpdateListner(Curentobj -> UpdateDateAndTime());

    }

    protected static void UpdateDateAndTime() {
        c = Calendar.getInstance();
        String h = c.getTime().getHours() + "", m = c.getTime().getMinutes() + "", s = c.getTime().getSeconds() + "";
        if (m.length() < 2)
            m = "0" + m;
        if (h.length() < 2)
            h = "0" + h;

        HoursMinutes.setText(h + ":" + m);
        // Seconds.setText(s);

        String dateStr;

        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        dateStr = df.format(Calendar.getInstance().getTime());
        Date.setText(dateStr);
    }


    public static void UpdateWidgets(double y) {
        if (y >= HoursMinutes.getTop()) {
            double CurentTime = y - HoursMinutes.getTop(), F_pos = Screen.Width / 50, T_pos = -Screen.Width / 2, Duration = ZipperTouchListener.Duration;
            if (CurentTime < Duration) {
                double newX = Transition.GetValue(ZipperTouchListener.transitiontype, CurentTime, F_pos, T_pos, Duration);
                HoursMinutes.setLeft(newX);
                BateryCover.setLeft(newX);
            }
            if (CurentTime < Duration / 2) {//HoursMinutes.setLeft(Screen.Width/50-(y-HoursMinutes.getTop()));
                double newA = Transition.GetValue(ZipperTouchListener.transitiontype, CurentTime, 255, 0, Duration / 1.4);
                HoursMinutes.setAlpha(newA);
                BateryCover.setAlpha(newA);
            }
        } else {
            HoursMinutes.setLeft(Screen.Width / 50);
            BateryCover.setLeft(Screen.Width / 80);
        }

        if (y >= Date.getTop()) {
            double CurentTime = y - Date.getTop(), F_pos = Screen.Width / 36, T_pos = -Screen.Width / 2, Duration = ZipperTouchListener.Duration;
            if (CurentTime < Duration) {
                double newX = Transition.GetValue(ZipperTouchListener.transitiontype, CurentTime, F_pos, T_pos, Duration);
                Date.setLeft(newX);
                if (CurentTime < Duration / 2) {
                    double newA = Transition.GetValue(ZipperTouchListener.transitiontype, CurentTime, 255, 0, Duration / 1.4);
                    Date.setAlpha(newA);
                }
            }//HoursMinutes.setLeft(Screen.Width/50-(y-HoursMinutes.getTop()));
        } else {
            Date.setLeft(Screen.Width / 36);
            Date.setAlpha(255);
        }
    }
}
