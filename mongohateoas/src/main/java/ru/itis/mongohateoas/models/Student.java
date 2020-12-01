package ru.itis.mongohateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Student {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer year;
    private String scholarship;
    @DBRef
    private StudentGroup studentGroup;
    @DBRef
    private List<Course> courses;
    private String state;
}
