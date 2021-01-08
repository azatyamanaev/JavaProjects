package ru.itis.querydslmongodb.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.querydslmongodb.models.QStudent;
import ru.itis.querydslmongodb.models.Student;

public interface AccountsRepository extends MongoRepository<Student, ObjectId>, QuerydslPredicateExecutor<Student>, QuerydslBinderCustomizer<QStudent> {
    @Override
    default void customize(QuerydslBindings bindings, QStudent qStudent) {
        bindings.bind(qStudent.courses.any().title).as("courses.title").first(
                StringExpression::containsIgnoreCase
        );
    }
}