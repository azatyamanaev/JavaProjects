package ru.itis.querydslmongodb.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.StudentGroup;

public interface GroupsRepository extends MongoRepository<StudentGroup, ObjectId> {
}
