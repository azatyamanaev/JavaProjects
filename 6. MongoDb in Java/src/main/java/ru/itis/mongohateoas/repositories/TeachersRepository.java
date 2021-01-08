package ru.itis.mongohateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.mongohateoas.models.Teacher;

@RepositoryRestResource
public interface TeachersRepository extends PagingAndSortingRepository<Teacher, ObjectId> {
}
