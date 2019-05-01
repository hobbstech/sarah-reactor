package io.github.hobbstech.sara_api.entertainment.music;

import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MusicRecordRepository;
import io.github.hobbstech.sarah_core_entertainment.music.service.MusicRecordService;
import io.github.hobbstech.sarah_core_entertainment.music.service.PlayingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
public class MusicRecordRestController {

    private final MusicRecordService musicRecordService;

    private final MusicRecordRepository musicRecordRepository;

    public MusicRecordRestController(MusicRecordService musicRecordService, MusicRecordRepository musicRecordRepository) {
        this.musicRecordService = musicRecordService;
        this.musicRecordRepository = musicRecordRepository;
    }

    @PostMapping("/v1/upload-music/{genre}")
    public MusicRecord uploadMusic(@RequestParam("file") MultipartFile file,
                                   @PathVariable("genre") String genre) {
        return musicRecordService.uploadMusicRecord(file, genre);
    }

    @GetMapping("/v1/play-mood-music/{moodId}")
    public void playMoodMusic(@PathVariable("moodId") Long moodId) {
        musicRecordService.playMoodMusic(moodId);
    }

    @GetMapping("/v1/music-records/mood/{moodId}")
    public Collection<MusicRecord> getMoodMusic(@PathVariable("moodId") Long moodId) {
        return musicRecordService.findMoodMusic(moodId);
    }

    @GetMapping("/v1/music-records")
    public Page<MusicRecord> findAll(@PageableDefault(size = 20, sort = "genre",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return musicRecordRepository.findAll(pageable);
    }

    @GetMapping("/v1/play-music-record/{recordId}")
    public void playSong(@PathVariable("recordId") Long recordId) {
        musicRecordService.playSong(recordId);
    }

    @GetMapping("/v1/stop-music")
    public void stopPlayingMusic() {
        musicRecordService.stopPlaying();
    }

    @GetMapping("/v1/playing-status")
    public PlayingStatus getPlayingStatus() {
        return musicRecordService.getPlayingStatus();
    }

}
