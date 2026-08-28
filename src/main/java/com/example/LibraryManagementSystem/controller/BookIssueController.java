package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entity.BookIssue;
import com.example.LibraryManagementSystem.service.BookIssueService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @PostMapping
    public BookIssue issueBook(
            @RequestParam Long bookId,
            @RequestParam Long memberId,
            @RequestParam String dueDate) {

        return bookIssueService.issueBook(
                bookId,
                memberId,
                LocalDate.parse(dueDate)
        );
    }

    @PutMapping("/{id}/return")
    public BookIssue returnBook(@PathVariable Long id) {
        return bookIssueService.returnBook(id);
    }

    @GetMapping("/issued")
    public List<BookIssue> getIssuedBooks() {
        return bookIssueService.getIssuedBooks();
    }

    @GetMapping("/overdue")
    public List<BookIssue> getOverdueBooks() {
        return bookIssueService.getOverdueBooks();
    }
}