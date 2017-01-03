package com.sxd.flappybird.flappybird;

import android.app.Activity;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by SXD on 2016/11/4.
 */

public class MusicManager {

    public MusicManager() {
       // MyConst.bgmMP.start();
        MyConst.bgmMP.start();

        MyConst.bgmMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer bgmMP) {
                bgmMP.start();
            }
        });
    }

    public void dead(){
        if(MyConst.bgmMP!=null&&MyConst.bgmMP.isPlaying()) {
            MyConst.bgmMP.pause();
        }
        MyConst.deadMP.start();
    }

}
