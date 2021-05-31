package ru.itis.neo4jexample.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Movie;

public interface MovieService {
    Mono<Movie> createOrUpdateMovie(Movie movie);
    Flux<Movie> getAllMovies();
    Mono<Movie> getMovieByTitle(String title);
    Mono<Void> deleteMovieById(Long id);
}
