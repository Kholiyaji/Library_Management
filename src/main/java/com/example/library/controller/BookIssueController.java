package com.example.library.controller;

import com.example.library.model.BookIssue;
import com.example.library.service.BookIssueService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookIssue> issueBook(
            @RequestParam Long bookId,
            @RequestParam Long memberId,
            @RequestParam LocalDate dueDate) {

        return ResponseEntity.ok(
                service.issueBook(bookId, memberId, dueDate)
        );
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BookIssue> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnBook(id));
    }

    @GetMapping("/issued")
    public ResponseEntity<List<BookIssue>> getIssuedBooks() {
        return ResponseEntity.ok(service.getAllIssued());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BookIssue>> getOverdueBooks() {
        return ResponseEntity.ok(service.getOverdue());
    }
}