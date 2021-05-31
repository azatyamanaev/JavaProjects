package ru.itis.neo4jexample.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Movie;
import ru.itis.neo4jexample.models.Person;

public interface PersonService {
    Mono<Person> createOrUpdatePerson(Person person);
    Flux<Person> getAllPeople();
    Mono<Person> getPersonByName(String name);
    Mono<Void> deletePersonById(Long id);
}
