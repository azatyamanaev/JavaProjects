package ru.itis.querydslmongodb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.Teacher;

public interface TeachersRepository extends MongoRepository<Teacher, Long> {
}
