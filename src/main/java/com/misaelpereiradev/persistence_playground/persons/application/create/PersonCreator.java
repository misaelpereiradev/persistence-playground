package com.misaelpereiradev.persistence_playground.persons.application.create;

import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import com.misaelpereiradev.persistence_playground.persons.domain.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonCreator {

    private final PersonRepository repository;

    public long create(Person person) {
        return repository.create(person);
    }
}
