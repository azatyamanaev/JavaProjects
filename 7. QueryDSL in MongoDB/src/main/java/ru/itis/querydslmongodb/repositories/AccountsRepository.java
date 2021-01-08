package ru.itis.querydslmongodb.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.querydslmongodb.models.Student;

public interface AccountsRepository extends MongoRepository<Student, ObjectId>, QuerydslPredicateExecutor<Student> {
}