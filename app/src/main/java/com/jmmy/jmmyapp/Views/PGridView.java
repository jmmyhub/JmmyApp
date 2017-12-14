package com.jmmy.jmmyapp.Views;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by jmmy on 2017/12/14.
 */

public class PGridView extends GridView {
    public PGridView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ev.getX();
        ev.getY();
        return super.onTouchEvent(ev);
    }

    @Override
    public int pointToPosition(int x, int y) {
        return super.pointToPosition(x, y);
    }
}
