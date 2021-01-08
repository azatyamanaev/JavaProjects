package ru.itis.mongohateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongohateoas.models.Course;


import java.util.List;

@RepositoryRestResource
public interface CoursesRepository extends PagingAndSortingRepository<Course, ObjectId> {
    @RestResource(path = "custom", rel = "custom")
    @Query(value = "{state: Published, count: 2}")
    Page<Course> findCustom(Pageable pageable);

    @RestResource(path = "byTitle", rel = "title")
    @Query(value = "{title: ?0}")
    List<Course> findAllByTitle(@Param("title") String title, Pageable pageable);
}
