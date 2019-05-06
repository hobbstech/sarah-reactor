package io.github.hobbstech.sarah_voice.chat;

import io.github.hobbstech.sarah_voice.tts.TTSTemplate;
import lombok.val;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VoiceQuestionsServiceImpl implements VoiceQuestionsService {

    @Override
    public Collection<String> getQuestions() {
        return Stream.of(Questions.values()).parallel().map(Questions::getQuestion).collect(Collectors.toSet());
    }

    @Override
    public void askQuestion(String question) {
        val response = PreliminaryResponses.RESPONSES.getOrDefault(question, "Sorry I do not understand the question");

        TTSTemplate.getDefaultInstance().speak(response);
    }
}
