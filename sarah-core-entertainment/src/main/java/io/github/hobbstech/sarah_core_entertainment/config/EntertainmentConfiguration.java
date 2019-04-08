package io.github.hobbstech.sarah_core_entertainment.config;

import io.github.hobbstech.sarah_core_entertainment.music.repository.MoodRepository;
import io.github.hobbstech.sarah_core_entertainment.music.repository.MusicRecordRepository;
import io.github.hobbstech.sarah_core_entertainment.music.service.MoodService;
import io.github.hobbstech.sarah_core_entertainment.music.service.MoodServiceImpl;
import io.github.hobbstech.sarah_core_entertainment.music.service.MusicRecordService;
import io.github.hobbstech.sarah_core_entertainment.music.service.MusicRecordServiceImpl;
import io.github.hobbstech.sarah_core_utils.files.FileStorageService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.sarah_core_entertainment")
@EntityScan(basePackages = "io.github.hobbstech.sarah_core_entertainment")
@EnableJpaRepositories(basePackages = "io.github.hobbstech.sarah_core_entertainment")
public class EntertainmentConfiguration {

    @Bean
    public MoodService moodService(MoodRepository moodRepository) {
        return new MoodServiceImpl(moodRepository);
    }

    @Bean
    public MusicRecordService musicRecordService(MusicRecordRepository musicRecordRepository,
                                                 FileStorageService fileStorageService,
                                                 MoodRepository moodRepository) {
        return new MusicRecordServiceImpl(musicRecordRepository, fileStorageService, moodRepository);
    }

}
