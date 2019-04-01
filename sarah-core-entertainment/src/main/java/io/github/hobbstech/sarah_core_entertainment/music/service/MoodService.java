package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.Mood;

import java.util.Collection;

public interface MoodService {

    Mood createMood(MoodDto moodDto);

    Mood update(MoodDto moodDto, Long id);

    Collection<Mood> findAll();

    Mood findById(Long id);

}
