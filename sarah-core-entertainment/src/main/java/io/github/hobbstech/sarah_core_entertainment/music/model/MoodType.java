package io.github.hobbstech.sarah_core_entertainment.music.model;

import lombok.Getter;

public enum MoodType {

    HAPPY("Happy"),
    GLORIFYING("Glorifying"),
    BORED("Bored"),
    TIRED("Tired"),
    SLEEPY("Sleepy"),
    JOVIAL("Jovial"),
    FOCUS("Focus"),
    HITS("Hits"),
    FEEL_GOOD("Feeling Good"),
    LONELY("Lonely"),
    CARING("Caring"),
    LOVED("loved"),
    AWESOME("Awesome"),
    RELAXING("relaxing"),
    ANXIOUS("Anxious"),
    HURT("Hurt"),
    SAD("Sad"),
    HEART_BROKEN("Heart Broken"),
    LOST("Lost");

    @Getter
    private final String name;


    MoodType(String name) {
        this.name = name;
    }
}
