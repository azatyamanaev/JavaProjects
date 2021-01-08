package ru.itis.querydslmongodb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.Course;


import java.util.List;

public interface CoursesRepository extends MongoRepository<Course, Long> {

}
