package com.lrm.service;

import com.lrm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    void deleteAllBook();
}
