package io.github.hobbstech.sara_api.voice;

import io.github.hobbstech.sarah_voice.tts.TTSTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoiceRestController {

    @PostMapping("/v1/speak")
    public void speak(@RequestBody String words) {
        TTSTemplate.getDefaultInstance().speak(words);
    }

}
