package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.minio")
@Data
public class MinioOssProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
