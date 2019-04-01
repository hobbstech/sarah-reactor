package io.github.hobbstech.sarah_core_entertainment.music.model;

import lombok.Getter;

public enum MoodType {

    HAPPY("Happy"),
    GLORIFYING("Glorifying"),
    BORED("Bored"),
    TIRED("Tired"),
    SLEEPY("Sleepy"),
    JOVIAL("Jovial");

    @Getter
    private final String name;


    MoodType(String name) {
        this.name = name;
    }
}
