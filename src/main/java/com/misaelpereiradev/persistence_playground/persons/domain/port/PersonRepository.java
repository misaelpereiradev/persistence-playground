package com.misaelpereiradev.persistence_playground.persons.domain.port;

import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll();
    Optional<Person> findById(long id);
    long create(Person person);
    void update(long id, Person person);
    void delete(long id);
}
