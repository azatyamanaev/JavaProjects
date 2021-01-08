package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.simplehateoasservice.models.Student;
import ru.itis.hateoas.simplehateoasservice.models.Teacher;

@RepositoryRestResource
public interface TeachersRepository extends PagingAndSortingRepository<Teacher, Long> {
    @RestResource(path = "teacher_students", rel = "teacher_students")
    @Query("from Student student join StudentGroup gr on student.studentGroup = gr join Teacher teacher on gr.curator = teacher where teacher.id = :teacherId")
    Page<Student> findAllStudentsOfTeacher(Pageable pageable, @Param("teacherId") Long teacherId);
}
