package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.Genre;
import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MoodRepository;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MusicRecordRepository;
import io.github.hobbstech.sarah_core_utils.files.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
public class MusicRecordServiceImpl implements MusicRecordService {

    private final MusicRecordRepository musicRecordRepository;

    private final FileStorageService fileStorageService;

    private final MoodRepository moodRepository;

    public MusicRecordServiceImpl(MusicRecordRepository musicRecordRepository, FileStorageService fileStorageService,
                                  MoodRepository moodRepository) {
        this.musicRecordRepository = musicRecordRepository;
        this.fileStorageService = fileStorageService;
        this.moodRepository = moodRepository;
    }

    @Override
    public MusicRecord uploadMusicRecord(MultipartFile multipartFile, final String genre) {

        val optionalDirectory =
                Stream.of(Genre.values())
                        .filter(mood1 -> mood1.getName()
                                .equalsIgnoreCase(genre)).limit(1)
                        .map(Genre::getDirectory)
                        .findFirst();

        String directory;

        if (optionalDirectory.isPresent())
            directory = optionalDirectory.get();
        else
            throw new IllegalArgumentException("Unknown genre supplied");

        val fileResponse = fileStorageService.storeFile(multipartFile, directory);
        val musicRecord = new MusicRecord();

        musicRecord.setFileType(fileResponse.getFileName().substring(fileResponse.getFileName().lastIndexOf(".") + 1));
        musicRecord.setFullPathName(fileResponse.getFullFileName());
        musicRecord.setRecordName(fileResponse.getFileName());
        musicRecord.setGenre(Genre.valueOf(genre.toUpperCase()));

        return musicRecordRepository.save(musicRecord);
    }

    @Override
    public void playMoodMusic(Long moodId) {

        val mood = moodRepository.findById(moodId)
                .orElseThrow(() -> new NoSuchElementException("Mood record was not found"));

        val musicRecords = musicRecordRepository.findAllByGenre(mood.getGenre());

        val musicFiles = musicRecords.stream().map(MusicRecord::getFullPathName).collect(toSet());

        log.info("{}", musicFiles);

        MusicPlayer.playAudioFiles(musicFiles);

    }

    @Override
    public void playSong(Long recordId) {

        val musicRecord = musicRecordRepository.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Music record was not found"));
        MusicPlayer.playAudioFile(musicRecord.getFullPathName());

    }

    @Override
    public void stopPlaying() {
        MusicPlayer.stopPlaying();
    }

    @Override
    public Collection<MusicRecord> findMoodMusic(Long moodId) {
        val mood = moodRepository.findById(moodId)
                .orElseThrow(() -> new NoSuchElementException("Mood record was not found"));
        return musicRecordRepository.findAllByGenre(mood.getGenre());
    }

    @Override
    public PlayingStatus getPlayingStatus() {

        boolean isSongComplete;

        try {
            isSongComplete = MusicPlayer.getInstance().getPlayer().isComplete();
        } catch (NullPointerException ex) {
            return PlayingStatus.STOPPED;
        }

        if (isSongComplete) {
            return PlayingStatus.STOPPED;
        } else {
            return PlayingStatus.PLAYING;
        }

    }
}
