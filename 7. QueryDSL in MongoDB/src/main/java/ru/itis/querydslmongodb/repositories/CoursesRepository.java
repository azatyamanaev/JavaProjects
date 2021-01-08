package ru.itis.querydslmongodb.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.Course;


import java.util.List;

public interface CoursesRepository extends MongoRepository<Course, ObjectId> {

}
