package com.example.library.repository;

import com.example.library.model.BookIssue;
import com.example.library.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    List<BookIssue> findByDueDateBeforeAndStatus(
            LocalDate date, IssueStatus status);

    List<BookIssue> findByStatus(IssueStatus issueStatus);
}