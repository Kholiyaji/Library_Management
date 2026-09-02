package com.saurabh.LibraryManagement.service;

import com.saurabh.LibraryManagement.model.BookEntity;
import com.saurabh.LibraryManagement.repo.BookRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookService {
    private static final Logger log= (Logger) LoggerFactory.getLogger(BookService.class);
    @Autowired
    
    private BookRepo bookrepo;

    public BookEntity addBook(BookEntity bookentity) {
        bookrepo.save(bookentity);
       return bookentity;
    }
    public BookEntity updateBook(BookEntity bookentity) {
        return bookrepo.save(bookentity);
    }
    public void deleteBook(Long bookid) {
        bookrepo.deleteById(bookid);
    }

    public Optional<BookEntity> viewBook( Long bookid) {
        return bookrepo.findById(bookid);
    }


    public BookEntity searchBook(String authorname, String booktitle, String genre, BookEntity bookentity) {
          if(authorname.equals(bookentity.getAuthorname()) || booktitle.equals(bookentity.getBooktitle()) || genre.equals(bookentity.getGenre())) {
              return bookentity;
          }else{
              return null;
          }
    }
}
