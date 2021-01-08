package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.simplehateoasservice.models.Institute;
import ru.itis.hateoas.simplehateoasservice.models.Student;

import java.util.List;

@RepositoryRestResource
public interface InstitutesRepository extends PagingAndSortingRepository<Institute, Long> {
    @RestResource(path = "byCity", rel = "city")
    List<Institute> findAllByCity(String city);

    @RestResource(path = "institute_students", rel = "institute_students")
    @Query("from Student student join StudentGroup gr on student.studentGroup = gr join Institute institute on gr.institute = institute where institute.id = :instituteId")
    Page<Student> findAllStudentsOfInstitute(Pageable pageable, @Param("instituteId") Long instituteId);
}
