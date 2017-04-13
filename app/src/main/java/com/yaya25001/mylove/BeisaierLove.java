package com.yaya25001.mylove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by toothwind on 2017/4/12.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class BeisaierLove extends View {
    public BeisaierLove(Context context) {
        super(context);
    }

    public BeisaierLove(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BeisaierLove(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    private int w;
    private int h;

    private Paint paintGray = new Paint();
    private Paint paintRed = new Paint();

    private void init() {
        paintGray.setColor(Color.GRAY);
        paintGray.setAntiAlias(false);
        paintGray.setStyle(Paint.Style.STROKE);
        paintGray.setStrokeWidth(2f);

        paintRed.setColor(Color.RED);
        paintRed.setAntiAlias(false);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(5f);
    }

    private int r = 400;
    private int m = (int) (400*0.551915024494f);

    private int up = 0;
    private int left = 0;
    private int down = 0;

    /*
    从x轴正方向 算 位置1
    逆时针 总共 12个点

    动:
    点4下降  up
    点8向右  left
    点12向左 left
    点9 11 上升 down

    10s内 up从0--->250  left 0--->30  down 0--->100

     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(w / 2, h / 2);
        canvas.scale(1, -1); //y轴反转

        Path path = new Path();
        path.moveTo(r, 0);                          //(点1)
        path.cubicTo(r, m, m, r, 0, r - up);    //(点2 3 4)
        path.cubicTo(-m, r, -r, m, -r, 0);          //( 5 6 7)
        path.cubicTo(-r + left, -m, -m, -r + down, 0, -r);        // 8 9 10
        path.cubicTo(m, -r + down, r - left, -m, r, 0);           //11 12 1

        canvas.drawPath(path, paintRed);


        up+=8;
        left+=1;
        down+=3;
        if (up > 250) {
            flag=false;
            return;
        }
        if (flag) {
            postInvalidateDelayed(200);
        }
    }

    private boolean flag = false;

    public void start() {
        flag = true;
        up = 0;
        left = 0;
        down = 0;
        invalidate();
    }
}
