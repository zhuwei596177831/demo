package com.example.mianshi.designpatterns.adapter;

/**
 * @author 朱伟伟
 * @date 2021-03-09 09:19:54
 * @description
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("play mp4 file:" + fileName);
    }
}
