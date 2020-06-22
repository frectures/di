package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {
	@Autowired
	private BookService bookService;

	@Test
	public void addOneBook() {
		bookService.addBook(new Book("Moby Dick", "Herman Melville", 1851));

		assertEquals(1, bookService.numberOfBooks());
	}

	@Test
	public void addTwoBooks() {
		bookService.addBook(new Book("A Midsummer Night's Dream", "William Shakespeare", 1596));
		bookService.addBook(new Book("Much Ado About Nothing", "William Shakespeare", 1599));

		assertEquals(2, bookService.numberOfBooks());
	}

	@Test
	public void booksWrittenByShakespeare() {
		bookService.addBook(new Book("A Midsummer Night's Dream", "William Shakespeare", 1596));
		bookService.addBook(new Book("Moby Dick", "Herman Melville", 1851));
		bookService.addBook(new Book("Much Ado About Nothing", "William Shakespeare", 1599));

		List<Book> shakespearesBooks = bookService.booksWrittenBy("William Shakespeare");

		assertEquals(List.of("William Shakespeare", "William Shakespeare"), shakespearesBooks.stream()
				.map(Book::getAuthor)
				.collect(Collectors.toList()));
	}
}
