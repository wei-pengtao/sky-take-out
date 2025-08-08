package com.sky.shop.controller;

import com.minio.oss.MinioOssUtil;
import com.sky.shop.domain.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Tag(name = "公共接口")
@RestController
@RequestMapping("/admin/common")
@RequiredArgsConstructor
public class CommonController {

    private final MinioOssUtil minioOssUtil;

    @PostMapping("/upload")
    @Operation(summary = "文件上传接口")
    public Result<String> upload(MultipartFile file) throws Exception {
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String objectName = UUID.randomUUID() + extension;

        String path = minioOssUtil.upload(file.getBytes(), objectName);
        return Result.success(path);
    }
}
