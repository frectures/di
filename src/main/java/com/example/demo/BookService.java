package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    // @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public long numberOfBooks() {
        return bookRepository.count();
    }

    public List<Book> booksWrittenBy(String author) {
        // artificial example; filtering by author should be done via findByAuthor!
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }
}
