package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
