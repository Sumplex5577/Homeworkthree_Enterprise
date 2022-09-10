package com.example.homeworkthree.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCsvDto {
    private Integer productId;
    private String productName;
    private Double price;
    private String status;
}
