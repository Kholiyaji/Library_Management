package com.saurabh.LibraryManagement.repo;

import com.saurabh.LibraryManagement.model.MemberEntity;
import org.apache.tomcat.jni.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {


}
