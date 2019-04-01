package io.github.hobbstech.sarah_core_entertainment.music.api;

import io.github.hobbstech.sarah_core_entertainment.music.model.Mood;
import io.github.hobbstech.sarah_core_entertainment.music.service.MoodDto;
import io.github.hobbstech.sarah_core_entertainment.music.service.MoodService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MoodRestController {

    private final MoodService moodService;

    public MoodRestController(MoodService moodService) {
        this.moodService = moodService;
    }

    @GetMapping("/v1/moods")
    public Collection<Mood> findAll() {
        return moodService.findAll();
    }

    @GetMapping("/v1/moods/{id}")
    public Mood findOneById(@PathVariable("id") Long id) {
        return moodService.findById(id);
    }

    @PostMapping("/v1/moods")
    public Mood createMood(@RequestBody MoodDto moodDto) {
        return moodService.createMood(moodDto);
    }

    @PutMapping("/v1/moods/{id}")
    public Mood updateMood(@PathVariable("id") Long id, @RequestBody MoodDto moodDto) {
        return moodService.update(moodDto, id);
    }

}
