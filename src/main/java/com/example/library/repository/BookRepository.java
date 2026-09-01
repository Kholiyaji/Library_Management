package com.example.library.repository;
import java.util.List;


import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface BookRepository extends JpaRepository<Book, Long> {
        List<Book> findByTitleContaining(String title);
        List<Book> findByAuthorContaining(String author);
        List<Book> findByGenreContaining(String genre);
    }

