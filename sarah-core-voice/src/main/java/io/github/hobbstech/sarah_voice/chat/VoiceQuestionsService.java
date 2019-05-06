package io.github.hobbstech.sarah_voice.chat;

import java.util.Collection;

public interface VoiceQuestionsService {

    Collection<String> getQuestions();

    void askQuestion(String question);
}
