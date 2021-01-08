package ru.itis.mongoexamples.template;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsRepository extends MongoRepository<Student, ObjectId> {
}
