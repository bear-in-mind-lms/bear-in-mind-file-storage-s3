package com.kwezal.bearinmind.filestorage.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BearInMindS3FileStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BearInMindS3FileStorageApplication.class, args);
    }
}
