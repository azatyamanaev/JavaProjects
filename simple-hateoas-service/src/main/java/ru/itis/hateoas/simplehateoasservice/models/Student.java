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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer year;
    private String scholarship;
    @OneToMany(mappedBy = "owner")
    private List<Document> documents;
    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;
    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;
    private String state;

    public void enroll() {
        if (this.state.equals("Enrollee") || this.state.equals("Expelled")) {
            this.state = "Undergraduate";
        } else if (this.state.equals("Graduate") || this.state.equals("Undergraduate")) {
            throw new IllegalStateException();
        }
    }

    public void expell() {
        if (this.state.equals("Undergraduate")) {
            this.state = "Expelled";
        } else if (this.state.equals("Graduate") || this.state.equals("Expelled") || this.state.equals("Enrollee")) {
            throw new IllegalStateException();
        }
    }

    public void increase() {
        if (this.scholarship.equals("Normal")) {
            this.scholarship = "Increased";
        } else if (this.scholarship.equals("Increased") || this.scholarship.equals("Absent")) {
            throw new IllegalStateException();
        }
    }

    public void reduce() {
        if (this.scholarship.equals("Increased")) {
            this.scholarship = "Normal";
        } else if (this.scholarship.equals("Normal") || this.scholarship.equals("Absent")) {
            throw new IllegalStateException();
        }
    }

    public void give() {
        if (this.scholarship.equals("Absent")) {
            this.scholarship = "Normal";
        } else if (this.scholarship.equals("Normal") || this.scholarship.equals("Increased")) {
            throw new IllegalStateException();
        }
    }

    public void deprive() {
        if (this.scholarship.equals("Increased") || this.scholarship.equals("Normal")) {
            this.scholarship = "Absent";
        } else if (this.scholarship.equals("Absent")) {
            throw new IllegalStateException();
        }
    }
}
