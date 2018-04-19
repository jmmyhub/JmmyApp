package com.jmmy.jmmyapp.jmmyview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class JmmyView extends View {
    public JmmyView(Context context) {
        super(context);
    }

    public JmmyView(Context context, @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JmmyView(Context context, @android.support.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
