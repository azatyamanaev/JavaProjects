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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer bonus;
    private String current;
    private String position;
    @OneToOne(mappedBy = "curator")
    private StudentGroup studentGroup;
    @ManyToMany
    @JoinTable(name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;

    public void suggest() {
        if (this.current.equals("Normal")) {
            this.current = "Suggested";
        } else if (this.current.equals("Suggested")) {
            throw new IllegalStateException();
        }
    }

    public void approve() {
        if (this.current.equals("Suggested")) {
            this.current = "Normal";
            this.bonus += 1;
        } else if (this.current.equals("Normal")) {
            throw new IllegalStateException();
        }
    }

    public void decline() {
        if (this.current.equals("Suggested")) {
            this.current = "Normal";
        } else if (this.current.equals("Normal")) {
            throw new IllegalStateException();
        }
    }

}
