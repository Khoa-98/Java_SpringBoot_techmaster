package com.example.onlab_02.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.example.onlab_02.dto.BookRequest;
import com.example.onlab_02.model.Book;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/book")
public class BookController {
    private ConcurrentHashMap<String, Book> books;

    public BookController() {
        books = new ConcurrentHashMap<>();
        books.put("0X-13", new Book("0X-13", "Gone with the wind", "Khoa", 1998));
        books.put("0X-14", new Book("0X-14", "Gone with the wind", "Khoa", 1997));
        books.put("0X-15", new Book("0X-15", "Chi Dau", "Nam Cao", 1943));
    }

    @GetMapping
    public List<Book> getBook() {
        return books.values().stream().toList();
    }

    @PostMapping
    public Book createNewBook(@RequestBody BookRequest bookRequest) {
        String uuid = UUID.randomUUID().toString();
        Book newBook = new Book(uuid, bookRequest.title(), bookRequest.author(), bookRequest.year());
        books.put(uuid, newBook);
        return newBook;
    }

    @GetMapping(value = "/searchById")
    public Book getBookById(@RequestParam String id) {
        return books.get(id);
    }

    @PutMapping(value = "/{id}")
    public Book updateBookById(@RequestParam("id") String id, @RequestBody BookRequest bookRequest) {
        Book updateBook = new Book(id, bookRequest.title(), bookRequest.author(), bookRequest.year());
        books.replace(id, updateBook);
        return updateBook;
    }

    @DeleteMapping(value = "/{id}")
    public Book deleteBookById(@PathVariable("id") String id) {
        Book removeBook = books.remove(id);
        return removeBook;
    }

}
