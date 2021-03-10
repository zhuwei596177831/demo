package com.example.mianshi.designpatterns.adapter;

/**
 * @author 朱伟伟
 * @date 2021-03-09 09:31:38
 * @description
 */
public class MediaPlayerTest {
    public static void main(String[] args) {
        MediaPlayerAdapter mediaPlayerAdapter = new MediaPlayerAdapter(new VlcPlayer());
        mediaPlayerAdapter.play("mp4", "sdvsd.mp4");
    }
}
