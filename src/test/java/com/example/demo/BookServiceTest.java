package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest
public class BookServiceTest {
	// @Autowired
	private BookService bookService;

	public BookServiceTest() {
		List<Book> BOOKS = List.of(new Book("A Midsummer Night's Dream", "William Shakespeare", 1596),
				new Book("Moby Dick", "Herman Melville", 1851),
				new Book("Much Ado About Nothing", "William Shakespeare", 1599));

		BookRepository bookRepositoryMock = new BookRepository() {

			@Override
			public <S extends Book> S save(S entity) {
				return null;
			}

			@Override
			public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
				return null;
			}

			@Override
			public Optional<Book> findById(Long aLong) {
				return Optional.empty();
			}

			@Override
			public boolean existsById(Long aLong) {
				return false;
			}

			@Override
			public Iterable<Book> findAll() {
				return BOOKS;
			}

			@Override
			public Iterable<Book> findAllById(Iterable<Long> longs) {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}

			@Override
			public void deleteById(Long aLong) {

			}

			@Override
			public void delete(Book entity) {

			}

			@Override
			public void deleteAll(Iterable<? extends Book> entities) {

			}

			@Override
			public void deleteAll() {

			}
		};
		bookService = new BookService(bookRepositoryMock);
	}

	@Test
	public void booksWrittenByShakespeare() {
		List<Book> shakespearesBooks = bookService.booksWrittenBy("William Shakespeare");

		assertEquals(List.of("William Shakespeare", "William Shakespeare"), shakespearesBooks.stream()
				.map(Book::getAuthor)
				.collect(Collectors.toList()));
	}
}

