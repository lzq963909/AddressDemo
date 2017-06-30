package com.bwie.addressdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by WuXirui
 * Create Time: 2017/6/23
 * Description:
 */

public class SlideBar extends View {
    private static final String TAG = "SlideBar";

    private String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    };

    private OnTouchDownListener listener;

    private TextView textView;

    private int choose = -1;
    private int oldChoose = -1;

    public void setListener(OnTouchDownListener listener) {
        this.listener = listener;
    }

    public SlideBar(Context context) {
        super(context);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(TextView textView) {
        this.textView = textView;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // 手指触摸的纵坐标
        float y = event.getY();
        // 计算公式,字母的位置 = y*字母的总个数/总高度
        int pos = (int) (y * letters.length / getHeight());
        oldChoose = choose;
        switch (action) {
            case MotionEvent.ACTION_UP: // 手指抬起
                // 把显示字母的textview隐藏
                if (textView != null) {
                    textView.setVisibility(View.INVISIBLE);
                }
                // 按下的位置设置成-1，未选中
                choose = -1;
                // 刷新，重绘
                invalidate();
                break;
            default: // 手指按下和移动
                // 在边界内，也就是在letters范围之内
                if (pos >= 0 && pos < letters.length) {
                    // 按下的时候显示 显示字母的textview，并且把字母设置成我们按下的位置的字母
                    if (textView != null) {
                        textView.setVisibility(VISIBLE);
                        textView.setText(letters[pos]);
                    }
                    // 手指按下和移动的时候把当前选中的字母传出去
                    if (listener != null) {
                        listener.onTouch(letters[pos]);
                    }
                    // 改变当前选中的字母
                    // 优化，只有当当前选中位置和上次选中的位置不同的时候才重绘
                    if (oldChoose != pos){
                        choose = pos;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: " + new Date(System.currentTimeMillis()));
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / letters.length;

        Paint bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor("#44000000"));
        bgPaint.setAntiAlias(true);
        canvas.drawPaint(bgPaint);
        bgPaint.reset();

        for (int i = 0; i < letters.length; i++) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#FFFFFF"));
            paint.setTextSize(30);
            // 字符占的宽度
            float f = paint.measureText(letters[i]);
            float xPos = width / 2 - f / 2;
            float yPos = singleHeight * (i + 1);
            if (i == choose) {
                paint.setColor(Color.parseColor("#FF0000"));
                paint.setFakeBoldText(true);
            }
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    interface OnTouchDownListener {
        void onTouch(String letter);
    }
}
