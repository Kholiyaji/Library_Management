package com.example.library.service;

import com.example.library.exception.LibraryException;
import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member addMember(Member member) {
        return repository.save(member);
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member getMember(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LibraryException("Member not found"));
    }

    public Member updateMember(Long id, Member member) {
        member.setId(id);
        return repository.save(member);
    }

    public void deleteMember(Long id) {

        if (!repository.existsById(id)) {
            throw new LibraryException("Member not found");
        }

        repository.deleteById(id);
    }
}