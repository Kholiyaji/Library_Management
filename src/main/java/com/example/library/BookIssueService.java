package com.example.library;

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

    public BookIssue issueBook(Long bookId, Long memberId, LocalDate dueDate) {

        Book book = bookRepo.findById(bookId).orElse(null);
        Member member = memberRepo.findById(memberId).orElse(null);

        if (book == null)
            throw new RuntimeException("Book not found");

        if (member == null)
            throw new RuntimeException("Member not found");

        if (book.getAvailableCopies() <= 0)
            throw new RuntimeException("Book not available");

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        BookIssue issue = new BookIssue();
        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(7));
        issue.setStatus(IssueStatus.ISSUED);

        return issueRepo.save(issue);
    }

    public BookIssue returnBook(Long id) {

        BookIssue issue = issueRepo.findById(id).orElse(null);

        if (issue == null) {
            return null;
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(IssueStatus.RETURNED);

        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        return issueRepo.save(issue);
    }

    public List<BookIssue> getAllIssued() {
        return issueRepo.findAll();
    }
    public List<BookIssue> getOverdue() {
        return issueRepo.findByDueDateBeforeAndStatus(
                LocalDate.now(), IssueStatus.ISSUED);
    }
}