package com.kwezal.bearinmind.filestorage.s3.service;

import static java.util.Objects.nonNull;

import com.amazonaws.services.s3.AmazonS3;
import com.kwezal.bearinmind.exception.InvalidRequestDataException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileStorageService {

    private final AmazonS3 s3client;

    @Value("${s3.endpointUrl}")
    private String endpointUrl;

    @Value("${s3.bucketName}")
    private String bucketName;

    public String upload(MultipartFile file, String fileAssetType, String identifier) {
        final var fileName = generateFileName(fileAssetType, identifier, getFileExtension(file));
        final var fileToSend = writeMultiPartToFile(file, fileName);
        s3client.putObject(bucketName, fileName, fileToSend);
        fileToSend.deleteOnExit();
        return endpointUrl + "/" + bucketName + "/" + fileName;
    }

    public void delete(String url) {
        final var lastIndexOfUrlSlash = url.lastIndexOf("/");
        if (lastIndexOfUrlSlash < 0 || lastIndexOfUrlSlash == url.length() - 1) {
            throw new InvalidRequestDataException("DeleteFileDto", Map.of("url", url));
        }

        final var fileName = url.substring(lastIndexOfUrlSlash + 1);
        s3client.deleteObject(bucketName, fileName);
    }

    private File writeMultiPartToFile(MultipartFile multipartFile, String fileName) {
        final var file = new File(fileName);
        try (final var fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private String generateFileName(String fileAssetType, String identifier, String fileExtension) {
        return fileAssetType + "_" + identifier + "." + fileExtension;
    }

    private String getFileExtension(MultipartFile file) {
        final var contentType = nonNull(file.getContentType())
            ? ContentType.parse(file.getContentType())
            : ContentType.IMAGE_JPEG;
        if (ContentType.IMAGE_PNG.equals(contentType)) {
            return "png";
        } else {
            return "jpg";
        }
    }
}
