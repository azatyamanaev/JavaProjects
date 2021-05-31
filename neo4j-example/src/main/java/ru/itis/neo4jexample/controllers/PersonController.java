package ru.itis.neo4jexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Person;
import ru.itis.neo4jexample.services.PersonService;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PutMapping("/people")
    Mono<Person> createOrUpdateMovie(@RequestBody Person person) {
        return personService.createOrUpdatePerson(person);
    }

    @GetMapping(value = { "/people" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Person> getPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/people/by-name")
    Mono<Person> byName(@RequestParam String name) {
        return personService.getPersonByName(name);
    }

    @DeleteMapping("/people/{id}")
    Mono<Void> delete(@PathVariable Long id) {
        return personService.deletePersonById(id);
    }
}
