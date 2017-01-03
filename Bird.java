package com.sxd.flappybird.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by SXD on 2016/11/3.
 */

public class Bird{
    private Paint paint;
    float x = MyConst.BIRD_START_X;
    float y = MyConst.BIRD_START_Y;
    float speed = 0;
    float width,height;
    boolean live = true;

    public Bird(){
        paint = new Paint();
        paint.setColor(Color.RED);
        width = 150;
        height = 120;
    }

    public void draw(Canvas canvas) {
        if(live) {
            canvas.drawBitmap(MyConst.birdPIC, null, new Rect((int)(x), (int)(y), (int)(x + width), (int)(y + height)), paint);
            //canvas.drawRect((int)(x), (int)(y), (int)(x + width), (int)(y + height), paint);
            move();
            paint.setTextSize(60);
            Rect targetRect = new Rect(0, 0, (int)MyConst.FRAME_WIDTH, 200);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Score: "+MyConst.SCORE.toString(), targetRect.centerX(), baseline, paint);
        }
        else{
            paint.setTextSize(180);
            Rect targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT-700)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT-700)/2)+1);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Game Over", targetRect.centerX(), baseline, paint);
           // canvas.drawText("Game Over",80,(MyConst.FRAME_HEIGHT-700)/2, paint);

            paint.setTextSize(100);
            targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT-200)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT-200)/2)+1);
            baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            canvas.drawText("Score: " + MyConst.SCORE.toString(), targetRect.centerX(), baseline, paint);
            //canvas.drawText("Score: " + MyConst.SCORE.toString(),200,(MyConst.FRAME_HEIGHT-200)/2, paint);

            paint.setTextSize(60);
            targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT+400)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT+400)/2)+1);
            baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            canvas.drawText("Touch the stupid face to restart", targetRect.centerX(), baseline, paint);
           // canvas.drawText("Touch the stupid face to restart",130,(MyConst.FRAME_HEIGHT+400)/2,paint);
          //  canvas.drawRect(MyConst.RESTART, paint);
            canvas.drawBitmap(MyConst.restartPIC, null, MyConst.RESTART, paint);
        }
    }

    public void move(){
        if(MyConst.PRESSING){
            speed = MyConst.FLY_SPEED;
            MyConst.PRESSING = false;
        }
        y += speed;
        speed += MyConst.G;
        if(y > MyConst.FRAME_HEIGHT) {
            live = false;
            MyConst.SCREEN_X = 0;
            MyConst.SCREEN_Y = 0;
        }
    }

    public Rect getRect(){
        return new Rect((int)(x), (int)(y), (int)(x + width), (int)(y + height));
    }
}
