package ru.itis.querydslmongodb.models;

import com.querydsl.core.annotations.QueryEntity;
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
@QueryEntity
@Document(collection = "institutes")
public class Institute {
    @Id
    private String _id;
    private String name;
    private String city;
    private String address;
    @DBRef
    private List<StudentGroup> studentGroups;
}
