package io.github.hobbstech.sarah_core_entertainment.music.service;


import io.github.hobbstech.sarah_voice.tts.TTSTemplate;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.nonNull;

@Slf4j
public class MusicPlayer {

    private static MusicPlayer musicPlayer;

    @Setter
    @Getter
    private Player player;

    private MusicPlayer() {
    }

    public static MusicPlayer getInstance() {
        if (musicPlayer == null) {
            synchronized (MusicPlayer.class) {
                if (musicPlayer == null) {
                    musicPlayer = new MusicPlayer();
                }
            }
        }
        return musicPlayer;
    }

    public static void playAudioFile(String fileName) {

        val musicPlayer = MusicPlayer.getInstance();

        try (FileInputStream inputStream = new FileInputStream(fileName)) {

            if (nonNull(musicPlayer.player) && !musicPlayer.player.isComplete()) {

                musicPlayer.player.close();

            }

            val bufferedStream = new BufferedInputStream(inputStream);

            musicPlayer.player = new Player(bufferedStream);

            musicPlayer.player.play();

        } catch (FileNotFoundException e) {

            log.info("---> File Not Found cause : {}", e.getMessage());
            TTSTemplate ttsTemplate = TTSTemplate.getDefaultInstance();
            ttsTemplate.speak("Requested music record or audio file was not found");

        } catch (IOException e) {

            log.info("---> IOException  : {}", e.getMessage());
            closePlayer();

        } catch (JavaLayerException e) {

            log.info("---> JavaLayerException cause : {}", e.getMessage());
            closePlayer();

        }

    }

    private static void closePlayer() {
        if (nonNull(musicPlayer.player))
            musicPlayer.player.close();
    }

    public static void playAudioFiles(Collection<String> fileNames) {
        boolean songPlayedToCompleteFlag = false;
        for (String audioFile : fileNames) {
            playAudioFile(audioFile);
            while (!songPlayedToCompleteFlag) {
                songPlayedToCompleteFlag = musicPlayer.player.isComplete();
            }
        }

    }

    public static void stopPlaying() {
        musicPlayer.player.close();
    }

}
