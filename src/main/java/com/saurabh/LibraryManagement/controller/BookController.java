package com.saurabh.LibraryManagement.controller;


import com.saurabh.LibraryManagement.model.BookEntity;
import com.saurabh.LibraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/BookManagement")
public class BookController {

    @Autowired
    private BookService  bookservice;

    @PostMapping("/addbook")
    public BookEntity addBook(@RequestBody BookEntity bookentity) {
     return bookservice.addBook(bookentity);
    }

    @PutMapping("/updatebook")
    public BookEntity updateBook(@RequestBody BookEntity bookentity) {

        return bookservice.updateBook(bookentity);
    }

    @DeleteMapping("/deletebook/{bookid}")
    public String deleteBook(@PathVariable Long bookid) {
        bookservice.deleteBook(bookid);
        return "deleted successfully";
    }

    @GetMapping("/viewbook/{bookid}")
    public Optional<BookEntity> viewBook(@PathVariable Long bookid) {
        return bookservice.viewBook(bookid);
    }

//    @GetMapping("/viewbooks")
//    public Optional<BookEntity> viewBooks(@PathVariable Long bookid) {
//        return bookservice.viewBook(bookid);
//    }

    @GetMapping("/searchbook")
    public BookEntity searchBook(@RequestParam String booktitle, @RequestParam String authorname, @RequestParam String genre, BookEntity bookentity) {
        return bookservice.searchBook(booktitle, authorname, genre,bookentity);
    }
}
