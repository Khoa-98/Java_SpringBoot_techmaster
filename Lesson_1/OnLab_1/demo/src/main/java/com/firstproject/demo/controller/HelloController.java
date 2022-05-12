package com.firstproject.demo.controller;

import javax.websocket.server.PathParam;

import com.firstproject.demo.model.Book;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
    return "Hello World, Spring Boot!";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book book() {
    return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Book book_xml() {
        return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
    }

    @GetMapping("/add/{a}/{b}")
    public int add(@PathVariable("a") int a, @PathVariable("b") int b) {
        return a + b;
    }

    @GetMapping("/add")
    @ResponseBody
    public int add2(@RequestParam("a") int a, @RequestParam("b") int b) {
    return a + b;
    }
    
    @GetMapping("/name/{your_name}")
    public String hi(@PathVariable("your_name") String yourName) {
        return "Hi " + yourName;
    }

    @GetMapping("/name/{your_name}")
    public String hi2(@PathParam("your_name") String yourName) {
        return "Hi " + yourName;
    }
}
