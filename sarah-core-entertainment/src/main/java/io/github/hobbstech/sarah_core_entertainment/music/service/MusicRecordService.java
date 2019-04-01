package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import org.springframework.web.multipart.MultipartFile;

public interface MusicRecordService {

    MusicRecord uploadMusicRecord(MultipartFile multipartFile, String genre);

    void playMoodMusic(Long moodId);

    void playSong(Long recordId);


}
