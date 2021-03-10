package com.example.mianshi.designpatterns.adapter;

/**
 * @author 朱伟伟
 * @date 2021-03-09 09:20:26
 * @description
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc file:" + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
