package com.example.minio;


import com.example.admin.config.ProjectConfig;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MinioConfig {

    @Resource
    private ProjectConfig projectConfig;

    @Bean
    public MinioClient minioClient(){
        ProjectConfig.MinioConfig minioConfig = projectConfig.getMinioConfig();
        String accessKey = minioConfig.getAccessKey();
        String secretKey = minioConfig.getSecretKey();
        String host = minioConfig.getHost();
        return MinioClient.builder()
                .endpoint(host)
                .credentials(accessKey,secretKey)
                .build();
    }
}
