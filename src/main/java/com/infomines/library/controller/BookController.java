package com.infomines.library.controller;

import com.infomines.library.dto.BookRequest;
import com.infomines.library.model.Book;
import com.infomines.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/search")
    public List<Book> searchByAuthor(@RequestParam String author) {
        return bookService.searchByAuthor(author);
    }

    @GetMapping("/available")
    public List<Book> getAvailable() {
        return bookService.getAvailable();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@Valid @RequestBody BookRequest request) {
        return bookService.create(request);
    }

    @PutMapping("/{id}")
    public Book update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}