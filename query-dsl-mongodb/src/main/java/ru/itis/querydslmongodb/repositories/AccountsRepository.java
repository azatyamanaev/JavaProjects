package ru.itis.querydslmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.querydslmongodb.models.Student;
import ru.itis.querydslmongodb.models.QStudent;

public interface AccountsRepository extends MongoRepository<Student, Long>, QuerydslPredicateExecutor<Student>, QuerydslBinderCustomizer<QStudent> {

}