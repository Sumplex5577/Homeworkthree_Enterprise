package com.example.homeworkthree.dtos;

import lombok.Data;

@Data
public class AddPersonDto {
    private String firstName;
    private String lastName;
    private String email;
}