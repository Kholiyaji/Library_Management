package com.example.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    List<BookIssue> findByDueDateBeforeAndStatus(
            LocalDate date, IssueStatus status);
}