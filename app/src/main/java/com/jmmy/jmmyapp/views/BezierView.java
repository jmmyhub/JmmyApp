package com.jmmy.jmmyapp.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jmmy on 2017/12/12.
 */

public class BezierView extends View {
    private Paint mPaint;
    private Path mPath;

    private Paint paint;
    private int viewWidth,viewHeight;//控件的宽和高
    private float commandX,commandY;//控制点的坐标
    private float waterHeight;//水位高度

    private boolean isInc;//控制点左移，还是右移；

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * 初始化画笔 路径
     */
    private void init() {
        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#AFDEE4"));

        //路径
        mPath = new Path();

        //辅助画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHeight = h;
        viewWidth = w;

        //控制点开始的Y坐标
        commandY = 7 / 8f * viewHeight;

        //终点开始的Y坐标，也就是水位水平高度，红色辅助线
        waterHeight = 15 / 16f * viewHeight;
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //起始点位置
        mPath.moveTo(-1 / 4f * viewWidth,viewHeight);
        //绘制水波浪
        mPath.quadTo(commandX,commandY,viewWidth + 1 / 4f * viewWidth,viewHeight);
        //绘制波浪下方闭合区域
        mPath.lineTo(viewWidth + 1 / 4f * viewWidth,viewHeight);
        mPath.lineTo(-1 / 4f * viewWidth,viewHeight);
        mPath.close();

        //绘制路径
        canvas.drawPath(mPath,mPaint);
        //绘制红色水位高度辅助线
        //canvas.drawLine(0,waterHeight,viewWidth,viewHeight,paint);
        //产生波浪左右涌动的感觉
        if (commandX >= viewWidth + 1 / 4f * viewWidth){ //控制点坐标大于等于终点坐标改标识
            isInc = false;
        } else if (commandX <= -1 / 4f * viewWidth){ //控制点坐标小于等于起点坐标改标识
            isInc =true;
        }
        commandX = isInc ? commandX +20 :commandX - 20;
        //水位不断加高，当距离控件顶端还有1/8的高度时，不再上升
        if (commandY >= 1 / 100f * viewHeight){
            commandY -= 2;
            waterHeight -= 2;
        }

        //路径重置
        mPath.reset();
        //重绘
        invalidate();
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(600,600);
        } else if (wSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(600,hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(wSpecSize,600);
        }
    }
}
