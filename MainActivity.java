package com.sxd.flappybird.flappybird;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;

public class MainActivity extends Activity{

//    private MediaPlayer bgmMP=new MediaPlayer();
    private MusicManager musicManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        MyConst.FRAME_WIDTH = metric.widthPixels;
        MyConst.FRAME_HEIGHT = metric.heightPixels;
        MyConst.RESTART = new Rect((int)(MyConst.FRAME_WIDTH/2 - 100), (int)((MyConst.FRAME_HEIGHT + 800)/2), (int)(MyConst.FRAME_WIDTH/2 + 100), (int)((MyConst.FRAME_HEIGHT + 1200)/2));

        MyConst.bgmMP = MediaPlayer.create(this, R.raw.bgm);
        MyConst.jumpMP = MediaPlayer.create(this, R.raw.jump);
        MyConst.deadMP = MediaPlayer.create(this, R.raw.dead);
        Resources res=getResources();
        MyConst.birdPIC = BitmapFactory.decodeResource(res, R.drawable.bird);
        MyConst.obstacleUpPIC = BitmapFactory.decodeResource(res, R.drawable.obstacleup);
        MyConst.obstacleDownPIC = BitmapFactory.decodeResource(res, R.drawable.obstacledown);
        MyConst.restartPIC = BitmapFactory.decodeResource(res, R.drawable.restart);
        MyConst.musicManager = new MusicManager();
        MySurfaceView view = new MySurfaceView(this);
   //     musicManager = new MusicManager();

//        MyConst.bgmMP.start();

//        MyConst.bgmMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
 //           @Override
 //           public void onCompletion(MediaPlayer bgmMP) {
 //               bgmMP.start();
 //           }
 //       });

    }

     @Override
     public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                MyConst.PRESSING = true;
                MyConst.SCREEN_X = event.getX();
                MyConst.SCREEN_Y = event.getY();

        //        if(MyConst.bird!=null&&MyConst.bird.live){
                    if(MyConst.jumpMP!=null) {
                        MyConst.jumpMP.release();
                    }
                    MyConst.jumpMP = MediaPlayer.create(this, R.raw.jump);
                    MyConst.jumpMP.start();
        //        }

                break;
            case MotionEvent.ACTION_UP:
                MyConst.PRESSING = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (MyConst.bgmMP != null) {
            MyConst.bgmMP.stop();
            MyConst.bgmMP.release();
        }
    }
}

