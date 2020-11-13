package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.simplehateoasservice.models.Student;

public interface StudentsRepository extends PagingAndSortingRepository<Student, Long> {
    @RestResource(path = "expelled", rel = "expelled")
    @Query("from Student student where student.state = 'Expelled'")
    Page<Student> findAllExpelledStudents(Pageable pageable);
}
