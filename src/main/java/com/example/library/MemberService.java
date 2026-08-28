package com.example.library;

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
        return repository.findById(id).orElse(null);
    }

    public Member updateMember(Long id, Member member) {
        member.setId(id);
        return repository.save(member);
    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }
}