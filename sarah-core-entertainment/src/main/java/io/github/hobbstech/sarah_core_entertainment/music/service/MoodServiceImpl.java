package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.Genre;
import io.github.hobbstech.sarah_core_entertainment.music.model.Mood;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MoodRepository;
import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import lombok.val;

import java.util.Collection;
import java.util.stream.Stream;

public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public Mood createMood(MoodDto moodDto) {

        val mood = new Mood();
        mood.setGenre(Stream.of(Genre.values()).filter(mood1 -> mood1.getName()
                .equalsIgnoreCase(moodDto.getGenre())).limit(1).findFirst().orElse(Genre.DEFAULT));
        mood.setName(moodDto.getName());
        return moodRepository.save(mood);
    }

    @Override
    public Mood update(MoodDto moodDto, Long id) {
        val mood = findById(id);
        mood.setGenre(Stream.of(Genre.values()).filter(mood1 -> mood1.getName()
                .equalsIgnoreCase(moodDto.getGenre())).limit(1).findFirst().orElse(Genre.DEFAULT));
        moodDto.setName(moodDto.getName());
        return moodRepository.save(mood);
    }

    @Override
    public Collection<Mood> findAll() {
        return moodRepository.findAll();
    }

    @Override
    public Mood findById(Long id) {
        return moodRepository.findById(id)
                .orElseThrow((() -> new RecordNotFoundException("Mood record was not found")));
    }
}
