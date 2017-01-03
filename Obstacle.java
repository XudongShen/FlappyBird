package com.sxd.flappybird.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by SXD on 2016/11/3.
 */

public class Obstacle {
    private Paint paint;
    float x, y;
    boolean passed = false;

    public Obstacle() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        x = MyConst.FRAME_WIDTH;
        y = (float)Math.random()*(MyConst.FRAME_HEIGHT - MyConst.OBSTACLE_HEIGHT*2) + MyConst.OBSTACLE_HEIGHT/2;
    }

    public void draw(Canvas canvas){
        if(MyConst.bird.live){
            //canvas.drawRect(x, 0, x + MyConst.OBSTACLE_WIDTH, y, paint);
            canvas.drawBitmap(MyConst.obstacleUpPIC, null, new Rect((int)x, (int)(y-4*MyConst.OBSTACLE_WIDTH), (int)(x+MyConst.OBSTACLE_WIDTH),(int)y),paint);
           // canvas.drawRect(x, y + MyConst.OBSTACLE_HEIGHT, x + MyConst.OBSTACLE_WIDTH, MyConst.FRAME_HEIGHT, paint);
            canvas.drawBitmap(MyConst.obstacleDownPIC, null, new Rect((int)x, (int)(y+MyConst.OBSTACLE_HEIGHT), (int)(x+MyConst.OBSTACLE_WIDTH),(int)(y+4*MyConst.OBSTACLE_WIDTH+MyConst.OBSTACLE_HEIGHT)),paint);
        }
        if(x <= -MyConst.OBSTACLE_WIDTH && MyConst.bird.live) {
            passed = true;
            MyConst.SCORE++;
            MyConst.OBSTACLE_SPEED -= 0.2;
        }
        move();
    }

    public void move(){
        x += MyConst.OBSTACLE_SPEED;
    }

    public Rect getRect_up(){
        return new Rect((int)x, 0, (int)(x + MyConst.OBSTACLE_WIDTH), (int)y);
    }

    public Rect getRect_down(){
        return new Rect((int)x, (int)(y + MyConst.OBSTACLE_HEIGHT), (int)(x + MyConst.OBSTACLE_WIDTH), (int)MyConst.FRAME_HEIGHT);
    }
}
