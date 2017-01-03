package com.sxd.flappybird.flappybird;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaPlayer;

/**
 * Created by SXD on 2016/11/3.
 */

public class MyConst {
    static boolean PRESSING;
    static float FRAME_WIDTH;
    static float FRAME_HEIGHT;
    static float SCREEN_X;
    static float SCREEN_Y;
    static MediaPlayer bgmMP;
    static MediaPlayer jumpMP;
    static MediaPlayer deadMP;
    static boolean DEAD_MP_PLAY;
    static MusicManager musicManager;
    static Bitmap birdPIC;
    static Bitmap bgPIC;
    static Bitmap obstacleUpPIC;
    static Bitmap obstacleDownPIC;
    static Bitmap restartPIC;
    static boolean START = false;
    static Rect RESTART;
    static Integer SCORE;
    static Bird bird;
    static final float G = 1.0f;
    static final float FLY_SPEED = -19;
    static final float BIRD_START_X = 280;
    static final float BIRD_START_Y = 300;
    static Obstacle obstacle1;
    static Obstacle obstacle2;
    static Obstacle obstacle_temp;
    static final float OBSTACLE_START_SPEED = -3.5f;
    static float OBSTACLE_SPEED;
    static final float OBSTACLE_HEIGHT = 450;
    static final float OBSTACLE_WIDTH = 400;
}
