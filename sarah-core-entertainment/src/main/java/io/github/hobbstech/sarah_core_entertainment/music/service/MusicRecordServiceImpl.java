package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.Genre;
import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MoodRepository;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MusicRecordRepository;
import io.github.hobbstech.sarah_core_utils.files.FileStorageService;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
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
    public MusicRecord uploadMusicRecord(MultipartFile multipartFile, String genre) {

        val optionalDirectory =
                Stream.of(Genre.values())
                        .filter(genre1 -> genre1.getName().equalsIgnoreCase(genre))
                        .limit(1)
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

        musicRecords.forEach(musicRecord -> {

            try {
                val resource = fileStorageService.loadFileAsResource(musicRecord.getRecordName(),
                        mood.getGenre().getDirectory());
                Media media = new Media(resource.getFile().toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();

            } catch (IOException e) {
                throw new IllegalStateException("Failed to play " + musicRecord.getRecordName() + " due to " + e.getMessage());
            }
        });

    }

    @Override
    public void playSong(Long recordId) {

        val musicRecord = musicRecordRepository.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Music record was not found"));
        Media media = new Media(musicRecord.getFullPathName());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();


    }
}
