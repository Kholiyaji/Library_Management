package com.example.library.service;

import com.example.library.exception.LibraryException;
import com.example.library.model.Book;
import com.example.library.model.BookIssue;
import com.example.library.model.IssueStatus;
import com.example.library.model.Member;
import com.example.library.repository.BookIssueRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookIssueService {

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
                .orElseThrow(() -> new LibraryException("Book not found"));

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member not found"));

        if (book.getAvailableCopies() <= 0) {
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

        return issueRepo.save(issue);
    }

    // Return Book
    public BookIssue returnBook(Long id) {

        BookIssue issue = issueRepo.findById(id)
                .orElseThrow(() -> new LibraryException("Issue not found"));

        if (issue.getStatus() == IssueStatus.RETURNED) {
            throw new LibraryException("Book already returned");
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(IssueStatus.RETURNED);

        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        return issueRepo.save(issue);
    }

    // Currently Issued Books
    public List<BookIssue> getAllIssued() {
        return issueRepo.findByStatus(IssueStatus.ISSUED);
    }

    // Overdue Books
    public List<BookIssue> getOverdue() {
        return issueRepo.findByDueDateBeforeAndStatus(
                LocalDate.now(),
                IssueStatus.ISSUED
        );
    }
}