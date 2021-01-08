package ru.itis.querydslmongodb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydslmongodb.models.Institute;


public interface InstitutesRepository extends MongoRepository<Institute, Long> {

}
