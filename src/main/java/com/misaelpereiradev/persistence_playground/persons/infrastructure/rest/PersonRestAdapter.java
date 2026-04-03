package com.misaelpereiradev.persistence_playground.persons.infrastructure.rest;

import com.misaelpereiradev.persistence_playground.persons.application.create.PersonCreator;
import com.misaelpereiradev.persistence_playground.persons.application.remove.PersonRemover;
import com.misaelpereiradev.persistence_playground.persons.application.retrieve.PersonRetriever;
import com.misaelpereiradev.persistence_playground.persons.application.update.PersonUpdater;
import com.misaelpereiradev.persistence_playground.persons.infrastructure.rest.dto.PersonDto;
import com.misaelpereiradev.persistence_playground.persons.infrastructure.rest.mapper.PersonDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonRestAdapter {

    private final PersonDtoMapper mapper;
    private final PersonCreator creator;
    private final PersonUpdater updater;
    private final PersonRetriever retriever;
    private final PersonRemover remover;

    @PostMapping
    public ResponseEntity<Long> createPerson(@RequestBody PersonDto person) {
        return Stream.of(person)
                .map(mapper::toDomain)
                .map(creator::create)
                .map(id -> new ResponseEntity<>(id, HttpStatus.CREATED))
                .findFirst()
                .orElseThrow();
    }

    @DeleteMapping("/{person-id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("person-id") Long personId) {
        remover.delete(personId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{person-id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("person-id") Long personId) {
        return Stream.of(personId)
                .map(retriever::getById)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseThrow();
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPersons() {
        return Stream.of(retriever.getAll())
                .map(mapper::toDtoList)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseThrow();
    }

    @PutMapping ("/{person-id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("person-id") Long personId, @RequestBody PersonDto person) {
        var personDomain = mapper.toDomain(person);
        updater.update(personId, personDomain);
        return ResponseEntity.noContent().build();
    }
}
