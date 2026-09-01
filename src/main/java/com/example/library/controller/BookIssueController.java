package com.example.library.controller;

import com.example.library.model.BookIssue;
import com.example.library.service.BookIssueService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class BookIssueController {

    private final BookIssueService service;

    public BookIssueController(BookIssueService service) {
        this.service = service;
    }

    @PostMapping
    public BookIssue issueBook(@RequestParam Long bookId,
                               @RequestParam Long memberId,
                               @RequestParam String dueDate) {
        return service.issueBook(
                bookId,
                memberId,
                LocalDate.parse(dueDate)
        );
    }

    @PutMapping("/{id}/return")
    public BookIssue returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }

    @GetMapping("/issued")
    public List<BookIssue> issuedBooks() {
        return service.getAllIssued();
    }
    @GetMapping("/overdue")
    public List<BookIssue> overdue() {
        return service.getOverdue();
    }
}