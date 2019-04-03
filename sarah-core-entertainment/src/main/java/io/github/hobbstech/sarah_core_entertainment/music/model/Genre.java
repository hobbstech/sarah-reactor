package io.github.hobbstech.sarah_core_entertainment.music.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genre {

    ALTERNATIVES("Alternatives", "alternatives"),
    ANIME("Anime", "anime"),
    BLUES("Blues", "blues"),
    CHILDREN_MUSIC("Children Music", "children-music"),
    CLASSICAL("Classical", "classical"),
    COMEDY("comedy", "comedy"),
    COUNTRY("Country", "country"),
    DANCE("Dance", "dance"),
    DISNEY("Disney", "disney"),
    EASY_LISTENING("Easy Listening", "easy-listening"),
    ELECTRONIC("Electronic", "electronic"),
    ENKA("Enka", "enka"),
    POP("POP", "pop"),
    FOLK_MUSIC("Folk Music", "folk-music"),
    JAZZ("Jazz", "jazz"),
    INSTRUMENTAL("Instrumental", "instrumental"),
    OPERA("Opera", "opera"),
    R_N_B("R n B", "rnb"),
    REGGAE("Reggae", "reggae"),
    ROCK("Rock", "rock"),
    VOCAL("Vocal", "vocal"),
    WORLD("World", "world"),
    SOUNDTRACK("Soundtrack", "soundtrack"),
    DEFAULT("Default", "default");

    @Getter
    private final String name;

    @Getter
    private final String directory;

    Genre(String name, String directory) {
        this.name = name;
        this.directory = directory;
    }
}
