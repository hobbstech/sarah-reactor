package io.github.hobbstech.sarah_voice.tts;

import org.junit.jupiter.api.Test;

class TTSTemplateTest {

    @Test
    void speak() {

        TTSTemplate ttsTemplate = TTSTemplate.getDefaultInstance();
        ttsTemplate.speak("Hello guys, are you tired yet");

    }
}