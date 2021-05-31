package ru.itis.neo4jexample.repositories;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Movie;

public interface MovieRepository extends ReactiveNeo4jRepository<Movie, Long> {
    Mono<Movie> findOneByTitle(String title);
}
