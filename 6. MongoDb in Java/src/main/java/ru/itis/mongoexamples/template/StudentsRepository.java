package ru.itis.mongoexamples.template;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsRepository extends MongoRepository<Student, Long> {
}
