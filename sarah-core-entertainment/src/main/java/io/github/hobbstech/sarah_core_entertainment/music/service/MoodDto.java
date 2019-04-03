package io.github.hobbstech.sarah_core_entertainment.music.service;

import io.github.hobbstech.sarah_core_entertainment.music.model.MoodType;
import lombok.Data;

@Data
public class MoodDto {

    private MoodType name;

    private String genre;

}
