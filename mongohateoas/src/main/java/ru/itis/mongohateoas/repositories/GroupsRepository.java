package ru.itis.mongohateoas.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongohateoas.models.StudentGroup;

public interface GroupsRepository extends MongoRepository<StudentGroup, Long> {
}
