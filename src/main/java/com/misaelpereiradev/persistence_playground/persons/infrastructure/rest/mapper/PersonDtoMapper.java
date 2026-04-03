package com.misaelpereiradev.persistence_playground.persons.infrastructure.rest.mapper;

import com.misaelpereiradev.persistence_playground.persons.domain.model.Person;
import com.misaelpereiradev.persistence_playground.persons.infrastructure.rest.dto.PersonDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    Person toDomain(PersonDto person);
    PersonDto toDto(Person person);
    List<Person> toDomainList(List<PersonDto> person);
    List<PersonDto> toDtoList(List<Person> person);
}
