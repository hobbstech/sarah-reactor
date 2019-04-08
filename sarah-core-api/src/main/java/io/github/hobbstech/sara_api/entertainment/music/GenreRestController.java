package io.github.hobbstech.sara_api.entertainment.music;

import io.github.hobbstech.sarah_core_entertainment.music.model.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreRestController {

    @GetMapping("/v1/genre")
    public Genre[] getGenres() {
        return Genre.values();
    }

}
