package com.sxd.flappybird.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SXD on 2016/11/3.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    Paint paint;
    Canvas canvas = null;
    private TimerTask task;
    private Timer timer;
    SurfaceHolder holder;

    private void init(){
        holder = getHolder();
        getHolder().addCallback(this);
        paint = new Paint();
//        reset();
    }

    public void reset(){
        MyConst.SCORE = 0;
        MyConst.bird = new Bird();
        MyConst.OBSTACLE_SPEED = MyConst.OBSTACLE_START_SPEED;
        MyConst.obstacle1 = new Obstacle();
        MyConst.obstacle2 = null;
        MyConst.DEAD_MP_PLAY = false;
        if(!MyConst.bgmMP.isPlaying()){
            MyConst.bgmMP.start();
        }
    }

    public void draw(){
        canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        MyConst.obstacle1.draw(canvas);
        if(MyConst.obstacle2!=null) {
            MyConst.obstacle2.draw(canvas);
        }
        MyConst.bird.draw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

   public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                if(MyConst.START) {
                    if (MyConst.bird.getRect().intersect(MyConst.obstacle1.getRect_up()) || MyConst.bird.getRect().intersect(MyConst.obstacle1.getRect_down())) {
                        MyConst.bird.live = false;
                        MyConst.SCREEN_X = 0;
                        MyConst.SCREEN_Y = 0;
                    }
                    if (MyConst.bird.live && (MyConst.obstacle1.x + MyConst.OBSTACLE_WIDTH / 2) < MyConst.BIRD_START_X && MyConst.obstacle2 == null) {
                        MyConst.obstacle2 = new Obstacle();
                    }
                    if (MyConst.bird.live && MyConst.obstacle1.passed) {
                        MyConst.obstacle_temp = MyConst.obstacle1;
                        MyConst.obstacle1 = MyConst.obstacle2;
                        MyConst.obstacle2 = MyConst.obstacle_temp;
                        MyConst.obstacle2 = null;
                    }
                    draw();
                    if(!MyConst.bird.live && !MyConst.DEAD_MP_PLAY){
                        MyConst.musicManager.dead();
                        MyConst.DEAD_MP_PLAY = true;
                    }
                    if (!MyConst.bird.live && MyConst.RESTART.contains((int) MyConst.SCREEN_X, (int) MyConst.SCREEN_Y)) {
                        reset();
                        //MyConst.PRESSING = false;
                    }
                }
                else {
                    canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    paint.setColor(Color.RED);
                    paint.setTextSize(180);
                    Rect targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT-400)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT-400)/2)+1);
                    Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                    int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                    paint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText("Flappy Bird", targetRect.centerX(), baseline, paint);
                    //canvas.drawText("Flappy Bird",90,(MyConst.FRAME_HEIGHT-400)/2, paint);

                    paint.setTextSize(100);
                    targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT+300)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT+300)/2)+1);
                    baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                    canvas.drawText("Touch to fly~", targetRect.centerX(), baseline, paint);
                    //canvas.drawText("Touch to fly",270,(MyConst.FRAME_HEIGHT+300)/2, paint);

                    paint.setTextSize(100);
                    targetRect = new Rect(0, (int)((MyConst.FRAME_HEIGHT+600)/2), (int)MyConst.FRAME_WIDTH, (int)((MyConst.FRAME_HEIGHT+600)/2)+1);
                    baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                    canvas.drawText("Touch to begin!", targetRect.centerX(), baseline, paint);
                   // canvas.drawText("Touch to begin!",200,(MyConst.FRAME_HEIGHT+600)/2, paint);
                    if(MyConst.PRESSING){
                        MyConst.START = true;
                        reset();
                    }
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
        };
        timer.schedule(task, 10, 16);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        task = null;
    }

    public MySurfaceView(Context context)
    {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startTimer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }
}
