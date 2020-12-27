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
@Document(collection = "courses")
public class Course {
    @Id
    private String _id;
    private String title;
    private String description;
    @DBRef
    private List<Student> students;
    private String state;
    @DBRef
    private List<Teacher> teachers;
    private Integer count;


    public void publish() {
        if (this.state.equals("Draft")) {
            this.state = "Published";
        } else if (this.state.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }

    public void increaseCount() {
        if (this.count == 1 || this.count == 2 || this.count == 0) {
            this.count += 1;
        } else {
            throw new IllegalStateException();
        }
    }

    public void decreaseCount() {
        if (this.count == 1 || this.count == 2 || this.count == 3) {
            this.count -= 1;
        } else {
            throw new IllegalStateException();
        }
    }
}
