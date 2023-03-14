package com.ventura013.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventura013.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
