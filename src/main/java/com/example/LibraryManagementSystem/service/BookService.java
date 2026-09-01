package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add Book
    public Book addBook(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepository.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get Book By ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        ));
    }

    // Update Book
    public Book updateBook(Long id, Book newBook) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        ));

        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setIsbn(newBook.getIsbn());
        book.setGenre(newBook.getGenre());
        book.setTotalCopies(newBook.getTotalCopies());
        book.setAvailableCopies(newBook.getAvailableCopies());

        return bookRepository.save(book);
    }

    // Delete Book
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        ));

        bookRepository.delete(book);
    }

    // Search By Title
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Search By Author
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    // Search By Genre
    public List<Book> searchByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}