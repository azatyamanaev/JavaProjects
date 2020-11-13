package ru.itis.hateoas.simplehateoasservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
    @OneToMany(mappedBy = "studentGroup")
    private List<Student> students;
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher curator;

}
