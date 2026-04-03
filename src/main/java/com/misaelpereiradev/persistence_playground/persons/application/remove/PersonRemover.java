package com.misaelpereiradev.persistence_playground.persons.application.remove;

import com.misaelpereiradev.persistence_playground.persons.application.retrieve.PersonRetriever;
import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import com.misaelpereiradev.persistence_playground.persons.domain.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonRemover {

    private final PersonRepository repository;
    private final PersonRetriever retriever;

    public void delete(long id) {
        Person currentPerson = retriever.getById(id);
        repository.delete(currentPerson.getId());
    }
}
