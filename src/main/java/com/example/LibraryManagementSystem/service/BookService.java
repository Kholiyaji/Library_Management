package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBook(Long id, Book newBook) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {

            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setIsbn(newBook.getIsbn());
            book.setGenre(newBook.getGenre());
            book.setTotalCopies(newBook.getTotalCopies());
            book.setAvailableCopies(newBook.getAvailableCopies());

            return bookRepository.save(book);
        }

        return null;
    }
    public void deleteBook(Long id)
    {
        bookRepository.deleteById(id);
    }
    public List<Book> searchByTitle(String title)
    {
        return bookRepository.findByTitle(title);
    }

    public List<Book> searchByGenre(String genre)
    {
        return bookRepository.findByGenre(genre);
    }

}