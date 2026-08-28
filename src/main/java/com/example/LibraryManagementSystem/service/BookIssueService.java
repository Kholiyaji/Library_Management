package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.BookIssue;
import com.example.LibraryManagementSystem.entity.Member;
import com.example.LibraryManagementSystem.repository.BookIssueRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BookIssueService(
            BookIssueRepository bookIssueRepository,
            BookRepository bookRepository,
            MemberRepository memberRepository) {

        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public BookIssue issueBook(Long bookId, Long memberId, LocalDate dueDate) {

        Book book = bookRepository.findById(bookId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);

        if (book == null || member == null) {
            return null;
        }

        if (book.getAvailableCopies() <= 0) {
            return null;
        }

        BookIssue issue = new BookIssue();

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(dueDate);
        issue.setStatus(BookIssue.Status.ISSUED);

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }

    public BookIssue returnBook(Long issueId) {

        BookIssue issue = bookIssueRepository.findById(issueId).orElse(null);

        if (issue == null) {
            return null;
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(BookIssue.Status.RETURNED);

        Book book = issue.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }

    public List<BookIssue> getIssuedBooks() {

        return bookIssueRepository.findByStatus(
                BookIssue.Status.ISSUED
        );
    }

    public List<BookIssue> getOverdueBooks() {

        List<BookIssue> issues =
                bookIssueRepository.findByStatus(BookIssue.Status.ISSUED);

        LocalDate today = LocalDate.now();

        for (BookIssue issue : issues) {

            if (issue.getDueDate().isBefore(today)) {

                issue.setStatus(BookIssue.Status.OVERDUE);

                bookIssueRepository.save(issue);
            }
        }

        return bookIssueRepository.findByStatus(
                BookIssue.Status.OVERDUE
        );
    }
}