package ru.itis.querydslmongodb.dto;

import lombok.Data;

@Data
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String courseName;
}
