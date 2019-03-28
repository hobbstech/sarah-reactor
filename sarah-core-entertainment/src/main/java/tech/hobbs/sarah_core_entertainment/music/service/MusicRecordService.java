package tech.hobbs.sarah_core_entertainment.music.service;

import org.springframework.web.multipart.MultipartFile;
import tech.hobbs.sarah_core_entertainment.music.model.Mood;

public interface MusicRecordService {

    String uploadMusicRecord(MultipartFile multipartFile, String genre);

    String playMusic(Long moodId);

    Mood createMood(MoodDto moodDto);

}
