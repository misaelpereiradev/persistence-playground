package com.misaelpereiradev.persistence_playground.persons.infrastructure.persistance;

import com.misaelpereiradev.jooq.tables.records.PersonsRecord;
import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonsRecordMapper {
    @Mapping(source = "idPerson", target = "id")
    Person toDomain(PersonsRecord person);
    @Mapping(source = "id", target = "idPerson")
    PersonsRecord toRecord(Person person);
    List<Person> toDomainList(List<PersonsRecord> person);
    List<PersonsRecord> toRecordList(List<Person> person);

    default Boolean map(Byte value) {
        return value == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    default Byte map(Boolean value) {
        return value ? (byte) 1 : (byte) 0;
    }
}
