package com.example.homeworkthree.dtos;

import lombok.Data;

@Data
public class UpdateProductDto {
    private String productName;
    private Double price;
}