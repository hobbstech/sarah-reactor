package io.github.hobbstech.sarah_core_utils.files;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FilesStorageConfigurationsProperties filesStorageConfigurations;

    public FileStorageServiceImpl(FilesStorageConfigurationsProperties filesStorageConfigurations) {
        this.filesStorageConfigurations = filesStorageConfigurations;
    }

    @Override
    public FileStorageResponse storeFile(MultipartFile multipartFile, String directory) {

        requireNonNull(directory, "The upload directory should not be null");
        requireNonNull(multipartFile, "The multipart file should not be null");

        val storageDir = filesStorageConfigurations.getStorageMusicFolder() + directory;

        val fileStorageLocation = Paths.get(storageDir).toAbsolutePath().normalize();

        if (!Files.exists(fileStorageLocation) || !Files.isDirectory(fileStorageLocation)) {

            try {
                Files.createDirectories(fileStorageLocation);
            } catch (IOException e) {
                log.error("---> Could not create directory due to : {}", e.getMessage());
            }

        }

        if (Objects.isNull(multipartFile.getOriginalFilename())) {
            log.error("---> Uploaded file should have an original name");
            throw new IllegalStateException("Uploaded file should have an original name");
        }
        val fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()).replace(" ", "-");

        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        Path targetLocation = fileStorageLocation.resolve(fileName);
        try {
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Failed to save the file due to : {}", e.getMessage());
        }

        return new FileStorageResponse(fileName, targetLocation.toAbsolutePath().toString());

    }

    @Override
    public Resource loadFileAsResource(String fileName, String directory) {

        val storageDir = filesStorageConfigurations.getStorageMusicFolder() + directory;

        val fileStorageLocation = Paths.get(storageDir).toAbsolutePath().normalize();

        try {

            Path filePath = fileStorageLocation.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {

                return resource;

            } else {

                throw new NoSuchElementException("File not found " + fileName);

            }

        } catch (MalformedURLException ex) {

            throw new NoSuchElementException("File not found " + fileName + " " + ex.getMessage());

        }
    }
}
