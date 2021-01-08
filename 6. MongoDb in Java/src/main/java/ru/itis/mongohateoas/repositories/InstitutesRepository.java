package ru.itis.mongohateoas.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongohateoas.models.Institute;

import java.util.List;

@RepositoryRestResource
public interface InstitutesRepository extends PagingAndSortingRepository<Institute, String> {
    @RestResource(path = "byCity", rel = "city")
    @Query(value = "{city: ?0}")
    List<Institute> findAllByCity(@Param("city") String city, Pageable pageable);
}
