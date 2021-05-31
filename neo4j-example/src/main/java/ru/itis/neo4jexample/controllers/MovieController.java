package ru.itis.neo4jexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Movie;
import ru.itis.neo4jexample.services.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PutMapping("/movies")
    Mono<Movie> createOrUpdateMovie(@RequestBody Movie newMovie) {
        return movieService.createOrUpdateMovie(newMovie);
    }

    @GetMapping(value = { "/movies" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/by-title")
    Mono<Movie> byTitle(@RequestParam String title) {
        return movieService.getMovieByTitle(title);
    }

    @DeleteMapping("/movies/{id}")
    Mono<Void> delete(@PathVariable Long id) {
        return movieService.deleteMovieById(id);
    }
}
