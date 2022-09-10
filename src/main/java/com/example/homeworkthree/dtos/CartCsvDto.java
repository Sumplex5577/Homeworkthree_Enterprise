package com.example.homeworkthree.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartCsvDto {
    private Integer id;
    private Integer ownerId;
    private String ownerName;
    private String ownerLastName;
    private String productName;
    private Double productPrice;
    private Double sum;
    private String status;

}
