package ru.itis.mongohateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongohateoas.models.Student;

public interface StudentsRepository extends PagingAndSortingRepository<Student, ObjectId> {
    @RestResource(path = "expelled", rel = "expelled")
    @Query(value = "{state: Expelled}")
    Page<Student> findAllExpelledStudents(Pageable pageable);
}
