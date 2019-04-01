package io.github.hobbstech.sarah_core_utils.files;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "files")
@PropertySource(value = "classpath:files-configurations.properties")
@Getter
@Setter
public class FilesStorageConfigurationsProperties {

    private String storageLocation;

    private String storageRoot;

    private String storageMusicFolder;


}
