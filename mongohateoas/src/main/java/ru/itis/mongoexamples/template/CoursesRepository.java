package ru.itis.mongoexamples.template;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursesRepository extends MongoRepository<Course, Long> {
}
