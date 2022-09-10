package com.example.homeworkthree.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCsvDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;

}
