package ru.itis.neo4jexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.neo4jexample.models.Person;
import ru.itis.neo4jexample.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Mono<Person> createOrUpdatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Flux<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Mono<Person> getPersonByName(String name) {
        return personRepository.findOneByName(name);
    }

    @Override
    public Mono<Void> deletePersonById(Long id) {
        return personRepository.deleteById(id);
    }
}
