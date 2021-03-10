package com.example.mianshi.designpatterns.adapter;

/**
 * @author 朱伟伟
 * @date 2021-03-09 09:24:00
 * @description
 */
public class MediaPlayerAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedMediaPlayer;

    public MediaPlayerAdapter(VlcPlayer vlcPlayer) {
        this.advancedMediaPlayer = vlcPlayer;
    }

    public MediaPlayerAdapter(Mp4Player mp4Player) {
        this.advancedMediaPlayer = mp4Player;
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "vlc":
                this.advancedMediaPlayer.playVlc(fileName);
                break;
            case "mp4":
                this.advancedMediaPlayer.playMp4(fileName);
                break;
            case "mp3":
                System.out.println("play mp3 file:" + fileName);
                break;
            default:
                System.out.println("未知的类型：" + audioType);
                break;
        }
    }
}
