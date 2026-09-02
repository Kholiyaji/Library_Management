package com.saurabh.LibraryManagement.service;


import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.repo.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import java.util.logging.Logger;

@Service
public class MemberService {
    private static final Logger log= LoggerFactory.getLogger(MemberService.class);
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

    public void deleteMember(Long memberid) {
        memberrepo.deleteById(memberid);


    }

}
