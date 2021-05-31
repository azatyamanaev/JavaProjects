package ru.itis.neo4jexample.repositories;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Person;

public interface PersonRepository extends ReactiveNeo4jRepository<Person, Long> {
    Mono<Person> findOneByName(String name);
}
