package com.infomines.library.service;

import com.infomines.library.dto.BookRequest;
import com.infomines.library.exception.BookNotFoundException;
import com.infomines.library.model.Book;
import com.infomines.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> getAvailable() {
        return bookRepository.findByAvailableTrueOrderByYearDesc();
    }

    public Book create(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setYear(request.year());
        book.setAvailable(request.available());

        return bookRepository.save(book);
    }

    public Book update(Long id, BookRequest request) {
        Book book = getById(id);

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setYear(request.year());
        book.setAvailable(request.available());

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }
}