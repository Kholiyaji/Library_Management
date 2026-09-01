package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Member;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Add Member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    // Get All Members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get Member By ID
    public Member getMemberById(Long id) {

        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id
                        ));
    }

    // Update Member
    public Member updateMember(Long id, Member newMember) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id
                        ));

        member.setName(newMember.getName());
        member.setEmail(newMember.getEmail());
        member.setPhoneNumber(newMember.getPhoneNumber());
        member.setMembershipDate(newMember.getMembershipDate());

        return memberRepository.save(member);
    }

    // Delete Member
    public void deleteMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id
                        ));

        memberRepository.delete(member);
    }
}