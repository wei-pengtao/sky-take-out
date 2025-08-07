package com.sky.employee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResultDTO<T> {
    private Long total;

    private List<T> records;
}
