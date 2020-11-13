package ru.itis.hateoas.simplehateoasservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    private String state;
    @ManyToMany(mappedBy = "courses")
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
