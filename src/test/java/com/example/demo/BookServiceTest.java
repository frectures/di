package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// @SpringBootTest
public class BookServiceTest {
	// @Autowired
	private BookService bookService;

	public BookServiceTest() {
		List<Book> BOOKS = List.of(new Book("A Midsummer Night's Dream", "William Shakespeare", 1596),
				new Book("Moby Dick", "Herman Melville", 1851),
				new Book("Much Ado About Nothing", "William Shakespeare", 1599));

		BookRepository bookRepositoryMock = mock(BookRepository.class);
		System.out.println(bookRepositoryMock.getClass());
		when(bookRepositoryMock.findAll()).thenReturn(BOOKS);
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

