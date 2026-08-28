package com.example.library;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class BookService {

        private final BookRepository repository;

        public BookService(BookRepository repository) {
            this.repository = repository;
        }

        public Book addBook(Book book) {
            return repository.save(book);
        }

        public List<Book> getAllBooks() {
            return repository.findAll();
        }

        public Book getBookById(Long id) {
            return repository.findById(id).orElse(null);
        }

        public Book updateBook(Long id, Book book) {
            book.setId(id);
            return repository.save(book);
        }

        public void deleteBook(Long id) {
            repository.deleteById(id);
        }

    public List<Book> search(String title) {
        return repository.findByTitleContaining(title);
    }
}

