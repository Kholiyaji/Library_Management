package com.saurabh.LibraryManagement.repo;

import com.saurabh.LibraryManagement.model.BookIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookissueRepo extends JpaRepository<BookIssueEntity, Long> {

}
