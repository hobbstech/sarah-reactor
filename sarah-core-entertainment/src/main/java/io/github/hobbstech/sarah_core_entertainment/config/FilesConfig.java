package io.github.hobbstech.sarah_core_entertainment.config;

import io.github.hobbstech.sarah_core_utils.files.FileStorageService;
import io.github.hobbstech.sarah_core_utils.files.FileStorageServiceImpl;
import io.github.hobbstech.sarah_core_utils.files.FilesStorageConfigurationsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.sarah_core_utils")
public class FilesConfig {

    @Bean
    public FileStorageService fileStorageService(FilesStorageConfigurationsProperties filesStorageConfigurationsProperties) {
        return new FileStorageServiceImpl(filesStorageConfigurationsProperties);
    }


}
