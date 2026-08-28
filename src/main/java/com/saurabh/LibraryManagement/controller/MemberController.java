package com.saurabh.LibraryManagement.controller;


import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.service.MemberService;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     public Optional<MemberEntity> viewMember(@PathVariable Long id) {
    return memberservice.viewMember(id);
     }
     public MemberEntity updateMember(@RequestBody MemberEntity memberentity) {
    return memberservice.updateMember(memberentity);
     }
     public String deleteMember(MemberEntity memberentity) {
      memberservice.deleteMember(memberentity);
      return "deleted";
     }

}
