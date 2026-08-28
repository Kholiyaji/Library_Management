package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    List<BookIssue> findByStatus(BookIssue.Status status);
}
