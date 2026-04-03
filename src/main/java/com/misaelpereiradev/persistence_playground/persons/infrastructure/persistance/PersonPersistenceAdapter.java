package com.misaelpereiradev.persistence_playground.persons.infrastructure.persistance;

import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import com.misaelpereiradev.persistence_playground.persons.domain.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.misaelpereiradev.jooq.Tables.PERSONS;

@Repository
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonRepository {

    private final DSLContext dsl;
    private final PersonsRecordMapper mapper;

    @Override
    public List<Person> findAll() {
        return dsl.selectFrom(PERSONS)
                .fetch()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Person> findById(long id) {
        return dsl.selectFrom(PERSONS)
                .where(PERSONS.ID_PERSON.eq(BigInteger.valueOf(id)))
                .fetch()
                .stream()
                .map(mapper::toDomain)
                .findFirst();
    }

    @Override
    public long create(Person person) {
        return dsl.insertInto(PERSONS)
                .set(mapper.toRecord(person))
                .returning(PERSONS.ID_PERSON)
                .fetchSingle()
                .getIdPerson()
                .longValue();
    }

    @Override
    public void update(long id, Person person) {
        dsl.update(PERSONS)
                .set(mapper.toRecord(person))
                .where(PERSONS.ID_PERSON.eq(BigInteger.valueOf(id)))
                .execute();
    }

    @Override
    public void delete(long id) {
        dsl.delete(PERSONS)
                .where(PERSONS.ID_PERSON.eq(BigInteger.valueOf(id)))
                .execute();
    }
}
