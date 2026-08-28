package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Member;
import com.example.LibraryManagementSystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateMember(Long id, Member newMember) {

        Member member = memberRepository.findById(id).orElse(null);

        if (member != null) {
            member.setName(newMember.getName());
            member.setEmail(newMember.getEmail());
            member.setPhoneNumber(newMember.getPhoneNumber());
            member.setMembershipDate(newMember.getMembershipDate());

            return memberRepository.save(member);
        }

        return null;
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
