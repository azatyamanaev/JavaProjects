package ru.itis.querydslmongodb.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.Institute;


public interface InstitutesRepository extends MongoRepository<Institute, ObjectId> {

}
