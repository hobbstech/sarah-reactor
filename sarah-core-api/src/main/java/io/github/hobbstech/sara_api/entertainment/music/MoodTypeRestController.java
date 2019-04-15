package io.github.hobbstech.sara_api.entertainment.music;

import io.github.hobbstech.sarah_core_entertainment.music.model.MoodType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoodTypeRestController {

    @GetMapping("/v1/mood-types")
    public MoodType[] getMoodTypes() {
        return MoodType.values();
    }

}
