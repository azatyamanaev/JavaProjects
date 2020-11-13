package ru.itis.hateoas.simplehateoasservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.simplehateoasservice.models.Course;

import java.util.List;

@RepositoryRestResource
public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {
    @RestResource(path = "published", rel = "published")
    @Query("from Course course where course.state = 'Published'")
    Page<Course> findAllPublished(Pageable pageable);

    @RestResource(path = "byTitle", rel = "title")
    List<Course> findAllByTitle(String title);
}
