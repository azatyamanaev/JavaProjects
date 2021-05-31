package ru.itis.neo4jexample.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Movie;
import ru.itis.neo4jexample.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Mono<Movie> createOrUpdateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Mono<Movie> getMovieByTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }

    @Override
    public Mono<Void> deleteMovieById(Long id) {
        return movieRepository.deleteById(id);
    }
}
