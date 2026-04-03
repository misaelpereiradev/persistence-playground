package com.misaelpereiradev.persistence_playground.persons.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private Boolean isStudent;

    public void update(Person person) {
        setName(person.getName());
        setAge(person.getAge());
        setIsStudent(person.getIsStudent());
    }
}
