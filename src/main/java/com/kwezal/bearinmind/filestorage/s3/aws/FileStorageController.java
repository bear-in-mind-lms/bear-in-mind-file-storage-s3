package com.kwezal.bearinmind.filestorage.s3.aws;

import com.kwezal.bearinmind.filestorage.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileStorageController implements FileApi {

    private final FileStorageService fileStorageService;

    @Override
    @PostMapping
    public ResponseEntity<String> upload(
        @RequestPart MultipartFile file,
        @RequestParam String fileAssetType,
        @RequestParam String identifier
    ) {
        return ResponseEntity.ok(fileStorageService.upload(file, fileAssetType, identifier));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String url) {
        fileStorageService.delete(url);
        return ResponseEntity.noContent().build();
    }
}
