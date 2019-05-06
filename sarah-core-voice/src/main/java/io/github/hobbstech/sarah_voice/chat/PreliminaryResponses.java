package io.github.hobbstech.sarah_voice.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PreliminaryResponses {

    public static Map<String, String> RESPONSES;

    static {

        RESPONSES = new HashMap<>();
        Stream.of(Questions.values()).forEach(questions -> RESPONSES.putIfAbsent(questions.getQuestion(), questions.getAnswer()));

    }

}
