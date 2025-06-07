package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.MinioOssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("/admin/common")
@RequiredArgsConstructor
public class CommonController {

    private final MinioOssUtil minioOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String objectName = UUID.randomUUID() + extension;

        String path = minioOssUtil.upload(file.getBytes(), objectName);
        return Result.success(path);
    }
}
