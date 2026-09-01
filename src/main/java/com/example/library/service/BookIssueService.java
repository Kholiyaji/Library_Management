package com.example.library.service;

import com.example.library.exception.LibraryException;
import com.example.library.model.Book;
import com.example.library.model.BookIssue;
import com.example.library.model.IssueStatus;
import com.example.library.model.Member;
import com.example.library.repository.BookIssueRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookIssueService {

    private static final Logger log =
            LoggerFactory.getLogger(BookIssueService.class);

    private final BookIssueRepository issueRepo;
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;

    public BookIssueService(BookIssueRepository issueRepo,
                            BookRepository bookRepo,
                            MemberRepository memberRepo) {
        this.issueRepo = issueRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    // Issue Book
    public BookIssue issueBook(Long bookId, Long memberId, LocalDate dueDate) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", bookId);
                    return new LibraryException("Book not found");
                });

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> {
                    log.error("Member not found with id: {}", memberId);
                    return new LibraryException("Member not found");
                });

        if (book.getAvailableCopies() <= 0) {
            log.error("Book is not available with id: {}", bookId);
            throw new LibraryException("Book not available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        BookIssue issue = new BookIssue();
        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(dueDate);
        issue.setStatus(IssueStatus.ISSUED);

        BookIssue savedIssue = issueRepo.save(issue);

        log.info("Book {} issued to member {}", bookId, memberId);

        return savedIssue;
    }

    // Return Book
    public BookIssue returnBook(Long id) {

        BookIssue issue = issueRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Issue not found with id: {}", id);
                    return new LibraryException("Issue not found");
                });

        if (issue.getStatus() == IssueStatus.RETURNED) {
            log.error("Book already returned for issue id: {}", id);
            throw new LibraryException("Book already returned");
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(IssueStatus.RETURNED);

        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        BookIssue returnedIssue = issueRepo.save(issue);

        log.info("Book returned successfully. Issue id: {}", id);

        return returnedIssue;
    }

    // Currently Issued Books
    public List<BookIssue> getAllIssued() {

        log.info("Fetching currently issued books");

        return issueRepo.findByStatus(IssueStatus.ISSUED);
    }

    // Overdue Books
    public List<BookIssue> getOverdue() {

        log.info("Fetching overdue books");

        return issueRepo.findByDueDateBeforeAndStatus(
                LocalDate.now(),
                IssueStatus.ISSUED
        );
    }
}