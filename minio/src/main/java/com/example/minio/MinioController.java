package com.example.minio;


import com.example.admin.config.ProjectConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RestController
@RequestMapping("/minio")
public class MinioController {

    @Resource
    private MinioUtils minioUtils;

    @PostMapping("/create")
    public void minio(MultipartFile file){
        minioUtils.upload(file);
    }

    @PostMapping("/download")
    public ResponseEntity download(String fileName){
        return minioUtils.download(fileName);
    }
}
