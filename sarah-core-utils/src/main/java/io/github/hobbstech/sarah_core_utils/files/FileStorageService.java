package io.github.hobbstech.sarah_core_utils.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    FileStorageResponse storeFile(MultipartFile multipartFile, String directory);

    Resource loadFileAsResource(String fileName, String directory);


}
