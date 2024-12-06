package com.example.admin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project.config")
public class ProjectConfig {

    private MinioConfig minioConfig = new MinioConfig();

    @Data
    public static class MinioConfig {
        private String host;
        private String bucket;
        private String accessKey;
        private String secretKey;
    }
}
