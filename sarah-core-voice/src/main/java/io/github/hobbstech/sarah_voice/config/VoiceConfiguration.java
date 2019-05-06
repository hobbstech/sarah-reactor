package io.github.hobbstech.sarah_voice.config;

import io.github.hobbstech.sarah_voice.chat.VoiceQuestionsService;
import io.github.hobbstech.sarah_voice.chat.VoiceQuestionsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoiceConfiguration {

    @Bean
    public VoiceQuestionsService voiceQuestionsService() {
        return new VoiceQuestionsServiceImpl();
    }
}
