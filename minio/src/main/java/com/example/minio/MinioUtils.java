package com.example.minio;

import com.example.admin.config.ProjectConfig;
import io.minio.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

@Component
public class MinioUtils {


    @Resource
    private ProjectConfig projectConfig;

    @Resource
    private MinioClient minioClient;

    /**
     * 判断桶(bucket)是否存在若不存在则创建一个桶(bucket)
     */
    public boolean existBucket(String name) {
        boolean found;
        try {
            //验证桶(bucket)是否存在
            found = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(name)
                            .build());
            if (!found) {
                //若不存在则创建一个桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                found = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            found = false;
        }
        return found;
    }

    /**
     * 创建一个桶(bucket)
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除一个桶
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void upload(MultipartFile file) {
        ProjectConfig.MinioConfig minioConfig = projectConfig.getMinioConfig();
        String bucket = minioConfig.getBucket();
        existBucket(bucket);
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(file.getOriginalFilename())
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResponseEntity<byte[]> download(String fileName) {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ProjectConfig.MinioConfig minioConfig = projectConfig.getMinioConfig();
        String bucket = minioConfig.getBucket();
        try {
            inputStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .build());
            byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(inputStream,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(Arrays.asList("*"));
            responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;

    }
}
