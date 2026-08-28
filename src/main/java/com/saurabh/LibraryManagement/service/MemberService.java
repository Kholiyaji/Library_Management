package com.saurabh.LibraryManagement.service;


import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepo memberrepo;

    public MemberEntity addMember(MemberEntity memberentity) {
        memberrepo.save(memberentity);
        return memberentity;
    }

    public Optional<MemberEntity> viewMember(Long memberid){
      return memberrepo.findById(memberid);
    }

    public MemberEntity updateMember(MemberEntity memberentity) {
         return memberrepo.save(memberentity);
    }

    public void deleteMember(MemberEntity memberentity) {
       memberrepo.delete(memberentity);


    }

}
