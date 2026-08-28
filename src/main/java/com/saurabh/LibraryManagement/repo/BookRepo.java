package com.saurabh.LibraryManagement.repo;

import com.saurabh.LibraryManagement.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookEntity,Long> {

}
