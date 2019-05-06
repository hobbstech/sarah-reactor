package io.github.hobbstech.sara_api.voice;

import io.github.hobbstech.sarah_voice.chat.VoiceQuestionsService;
import io.github.hobbstech.sarah_voice.tts.TTSTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class VoiceRestController {

    private final VoiceQuestionsService voiceQuestionsService;

    public VoiceRestController(VoiceQuestionsService voiceQuestionsService) {
        this.voiceQuestionsService = voiceQuestionsService;
    }

    @PostMapping("/v1/speak")
    public void speak(@RequestBody String words) {
        TTSTemplate.getDefaultInstance().speak(words);
    }

    @GetMapping("/v1/questions")
    public Collection<String> getQuestions() {
        return voiceQuestionsService.getQuestions();
    }

    @PostMapping("/v1/ask-question")
    public void askQuestion(@RequestParam("qsn") String question) {
        voiceQuestionsService.askQuestion(question);
    }

}
