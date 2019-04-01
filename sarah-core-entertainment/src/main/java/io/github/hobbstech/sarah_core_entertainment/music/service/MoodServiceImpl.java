package io.github.hobbstech.sarah_core_entertainment.music.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_entertainment.music.model.Mood;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MoodRepository;
import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public Mood createMood(MoodDto moodDto) {

        val mood = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(moodDto, Mood.class);
        return moodRepository.save(mood);
    }

    @Override
    public Mood update(MoodDto moodDto, Long id) {
        val mood = findById(id);
        mood.setGenre(moodDto.getGenre());
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
