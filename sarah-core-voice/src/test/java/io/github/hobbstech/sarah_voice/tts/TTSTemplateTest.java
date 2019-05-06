package io.github.hobbstech.sarah_voice.tts;

import org.junit.jupiter.api.Test;

class TTSTemplateTest {

    @Test
    void speak() {

        TTSTemplate ttsTemplate = TTSTemplate.getDefaultInstance();
        ttsTemplate.speak("Hi guys. I am Friday. i am your house assistant! We are still in development mode");

    }

    @Test
    void showVoices() {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.getAvailableVoices().forEach(System.out::println);
    }
}