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
@Document(collection = "groups")
public class StudentGroup {
    @Id
    private String _id;
    private String name;
    @DBRef
    private Institute institute;
    @DBRef
    private List<Student> students;
    @DBRef
    private Teacher curator;

}
