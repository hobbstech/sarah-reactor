package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

public interface MusicRecordService {

    MusicRecord uploadMusicRecord(MultipartFile multipartFile, String genre);

    @Async
    void playMoodMusic(Long moodId);

    @Async
    void playSong(Long recordId);

    void stopPlaying();
}
