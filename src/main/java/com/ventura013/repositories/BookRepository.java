package com.ventura013.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventura013.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
