package com.saurabh.LibraryManagement.controller;


import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.service.MemberService;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Optional;

@RestController
@RequestMapping("/member-management")
public class MemberController {
@Autowired
    private MemberService memberservice;

@PostMapping("/addmember")
    public MemberEntity addMember(@RequestBody MemberEntity memberentity) {
    return memberservice.addMember(memberentity);
}
@GetMapping("/viewmember/{memberid}")
     public Optional<MemberEntity> viewMember(@PathVariable Long memberid) {
    return memberservice.viewMember(memberid);
     }

     @PutMapping("/updatemember")
     public MemberEntity updateMember(@RequestBody MemberEntity memberentity) {
    return memberservice.updateMember(memberentity);
     }
     @DeleteMapping("/deletemember/{memberid}")
     public String deleteMember(@PathVariable Long memberid) {
      memberservice.deleteMember(memberid);
       return"deleted successfully";
     }

}
