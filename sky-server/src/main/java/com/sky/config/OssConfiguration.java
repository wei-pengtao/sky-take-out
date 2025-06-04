package com.sky.config;

import com.sky.properties.MinioOssProperties;
import com.sky.utils.MinioOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS配置类
 * 该类用于配置对象存储服务（OSS）的相关属性和功能。
 */
@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MinioOssUtil minioOssUtil(MinioOssProperties minioOssProperties) {
        return new MinioOssUtil(
                minioOssProperties.getEndpoint(),
                minioOssProperties.getAccessKey(),
                minioOssProperties.getSecretKey(),
                minioOssProperties.getBucketName()
        );
    }
}
