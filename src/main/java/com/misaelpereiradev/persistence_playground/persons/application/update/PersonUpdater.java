package com.misaelpereiradev.persistence_playground.persons.application.update;

import com.misaelpereiradev.persistence_playground.persons.application.retrieve.PersonRetriever;
import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import com.misaelpereiradev.persistence_playground.persons.domain.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonUpdater {

    private final PersonRepository repository;
    private final PersonRetriever retriever;

    public void update(long id, Person person) {
        Person currentPerson = retriever.getById(id);
        currentPerson.update(person);
        repository.update(currentPerson.getId(), currentPerson);
    }
}
