package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Shapes;

import java.util.ArrayList;
import java.util.List;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.Deplace;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.Fade;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.Rotation;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Animations.Sizer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Urect {

    public static List<Urect> list = new ArrayList<>();

    public boolean DrawChilds = true;
    Fade fade;
    Deplace deplace;
    Rotation rotation;
    Sizer sizer;

    public double skewX, skewY;

    public void setFadeAnnimation(Fade fade) {
        if (this.fade != null)
            this.fade.Remove();
        this.fade = fade;
    }

    public void setDeplaceAnnimation(Deplace deplace) {
        if (this.deplace != null) {
            this.deplace.remove();
        }
        this.deplace = deplace;
    }

    public void setRotationAnimation(Rotation rotation) {
		/*if(this.rotation!=null)
			this.rotation.remove();*/
        this.rotation = rotation;
    }

    public void setSizerAnnimation(Sizer sizer) {
        this.sizer = sizer;
    }


    protected double x, y, height, width, rotate, alpha, radius;
    public Paint paint;
    private Urect parent;
    private List<Urect> childrens = new ArrayList<>();
    public int color;

    public Urect(double x, double y, double width, double height, int bgColor) {
        this(x, y, width, height);
        this.paint.setColor(bgColor);
        color = bgColor;
    }

    public Urect(double x, double y, double width, double height) {
        super();

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.paint = new Paint();
        this.setAlpha(255);
        this.paint.setColor(Color.TRANSPARENT);
        color = Color.TRANSPARENT;
        list.add(this);
    }

    public Urect(double x, double y, double width, double height, boolean addtoList) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.paint = new Paint();
        this.setAlpha(255);
        this.paint.setColor(Color.TRANSPARENT);
        color = Color.TRANSPARENT;
        if (addtoList)
            list.add(this);
    }


    public boolean Clicked = false;

    //----------------------------------  Size seters geters  -----------------------------------------
    public double getRadius() {
        return radius;
    }

    public double getLeft() {
        return parent == null ? x : parent.getLeft() + x;
    }

    public double getRelativeLeft() {
        return x;
    }

    public void setLeft(double x) {
        this.x = x;
    }

    public double getTop() {
        return parent == null ? y : parent.getTop() + y;
    }

    public double getRelativeTop() {
        return y;
    }

    public void setTop(double y) {
        this.y = y;
    }

    public double getRight() {
        return parent == null ? getRelativeLeft() + Width() : parent.getLeft() + getRelativeLeft() + Width();
    }

    public double getBottom() {
        return parent == null ? y + Height() : parent.getTop() + y + Height();
    }

    //.........................................................................................................

    public double Height() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double Width() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    //.........................................................................................................

    public double getRotate() {
        //return parent==null?rotate:parent.rotate+rotate;
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public double getAlpha() {
        //return parent==null?alpha:parent.getAlpha()+alpha;
        if (parent != null)
            return (alpha / 255) * parent.getAlpha();
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
    //------------------------------------------------ color ----------------------------------------------

    public int getColor() {
        return paint.getColor();
    }

    public void setColor(int bgColor) {
        this.paint.setColor(bgColor);
        color = bgColor;
    }

    public List<Urect> getChildrens() {
        return childrens;
    }


    public void removeParent() {
        if (parent != null) {
            parent = null;
        }
    }

    public void AddChild(Urect child) {
        if (childrens == null)
            childrens = new ArrayList<>();
        childrens.add(child);
        child.parent = this;
    }

    public void DeleteChild(Urect child) {
        if (child.getParent() != this)
            return;
        if (childrens != null)
            childrens.remove(child);
        child.removeParent();
    }

    public Urect getParent() {
        return parent;
    }

    //------------------------------------------------- Graphic methods ----------------------------------------
    public Rect GetRect() {
        return new Rect((int) getLeft(), (int) getTop(), (int) getRight(), (int) getBottom());
    }

    public RectF GetRectF() {
        return new RectF((float) getLeft(), (float) getTop(), (float) getRight(), (float) getBottom());
    }

    public double GetCenterX() {
        return (getLeft() + getRight()) / 2;
    }

    public double getCenterY() {
        return (getTop() + getBottom()) / 2;
    }


    public void Draw(Canvas canvas) {

        int i = canvas.save();
        canvas.rotate((int) getRotate(), (int) GetCenterX(), (int) getCenterY());
        canvas.skew((int) skewX, (int) skewY);

        paint.setAlpha((int) getAlpha());
        if (color != Color.TRANSPARENT) {
            if (getRadius() == 0)
                canvas.drawRect(GetRect(), paint);
            else
                canvas.drawRoundRect(GetRectF(), (float) radius, (float) radius, paint);
        }
        canvas.restoreToCount(i);
        drawChildrens(canvas);

    }

    protected void drawChildrens(Canvas canvas) {
        if (DrawChilds) {
            if (childrens != null) {
                for (int j = 0; j < childrens.size(); j++) {
                    if (childrens.get(j) != null)
                        childrens.get(j).Draw(canvas);
                }
            }
        }
    }


    public boolean IsClicked(double x, double y) {
        return x >= getLeft() && x <= getRight() && y >= getTop() && y <= getBottom();
    }


    // -------------------------------------- Listners Events Deleges ----------------------------------------

    List<ClickDownListner> ClickDownlisteners;
    List<ClickUpListner> ClickUplisteners;
    List<TouchMoveListner> TouchMovelisteners;
    List<UpdateListner> UpdateListners;


    //------------------------------------------ Add listners ---------------------------------------------
    public void addOnClickDownListner(ClickDownListner toAdd) {
        if (ClickDownlisteners == null)
            ClickDownlisteners = new ArrayList<>();
        ClickDownlisteners.add(toAdd);
    }

    public void addOnClickUpListner(ClickUpListner toAdd) {
        if (ClickUplisteners == null)
            ClickUplisteners = new ArrayList<>();
        ClickUplisteners.add(toAdd);
    }

    public void addOnTouchMoveListner(TouchMoveListner toAdd) {
        if (TouchMovelisteners == null)
            TouchMovelisteners = new ArrayList<>();
        TouchMovelisteners.add(toAdd);
    }

    public void OnUpdateListner(UpdateListner toAdd) {
        if (UpdateListners == null)
            UpdateListners = new ArrayList<>();
        UpdateListners.add(toAdd);
    }


    //------------------------------------------  interfaces ----------------------------------------
    public interface ClickDownListner {
        void OnClickDownDo(double X, double Y);
    }

    public interface ClickUpListner {
        void OnClickUpDo(double X, double Y);
    }

    public interface TouchMoveListner {
        void OnMoveDo(Urect Curentobj, double X, double Y);
    }

    public interface UpdateListner {
        void Update(Urect Curentobj);
    }


    ///----------------------------------------- Event triggers ----------------------------------------

    //------------------------------------------all rects triggers--------------------------------------

    public static void CheckRectsClickUp() {

        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).checkClickUp();
        }
    }

    public static void CheckRectTouchMove(double X, double Y) {
        for (int i = list.size() - 1; i >= 0; i--) {
            try {
                list.get(i).checkTouchMove(X, Y);
            } catch (Exception e) {
                // TODO Auto-generated catch block
            }
        }
    }

    //------------------------------------------Event lunchers------------------------------------------
    public boolean checkClickDown(double x, double y) {

        for (int i = childrens.size() - 1; i >= 0; i--) {

            if (childrens.get(i).checkClickDown(x, y))
                return true;
        }

        boolean clickedRuned = false;
        if (IsClicked(x, y) && ClickDownlisteners != null) {
            for (int i = 0; i < ClickDownlisteners.size(); i++) {
                ClickDownlisteners.get(i).OnClickDownDo(x, y);
                clickedRuned = true;
            }
            Clicked = true;
        }
        return clickedRuned;
    }

    public void checkClickUp() {
        if (Clicked && ClickUplisteners != null) {
            for (int i = 0; i < ClickUplisteners.size(); i++) {
                ClickUplisteners.get(i).OnClickUpDo(x, y);
            }
            Clicked = false;
        }

    }

    public void checkTouchMove(double x, double y) {
        if (TouchMovelisteners != null) {
            for (int i = 0; i < TouchMovelisteners.size(); i++) {
                TouchMovelisteners.get(i).OnMoveDo(this, x, y);
            }
        }
    }


    public void CheckObjUpdates() {
        if (UpdateListners != null) {
            for (int i = UpdateListners.size() - 1; i >= 0; i--) {
                UpdateListners.get(i).Update(this);
            }
        }
        if (childrens != null) {

            for (int i = childrens.size() - 1; i >= 0; i--) {
                childrens.get(i).CheckObjUpdates();
            }
        }
    }

    public void Delete() {
        if (parent != null)
            parent.DeleteChild(this);
        list.remove(this);
        if (ClickDownlisteners != null)
            ClickDownlisteners.clear();
        if (ClickUplisteners != null)
            ClickUplisteners.clear();
        if (TouchMovelisteners != null)
            TouchMovelisteners.clear();
        if (UpdateListners != null)
            UpdateListners.clear();

    }

    public void clearChilds() {
        for (int i = 0; i < childrens.size(); i++) {
            childrens.get(i).Delete();
        }
    }

}
