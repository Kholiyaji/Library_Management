package com.saurabh.LibraryManagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="Member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberid;
    private String membername;
    private String memberemail;
    private Long phone_no;
    private LocalDate issuedate;

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberemail() {
        return memberemail;
    }

    public void setMemberemail(String memberemail) {
        this.memberemail = memberemail;
    }

    public Long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Long phone_no) {
        this.phone_no = phone_no;
    }

    public LocalDate getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(LocalDate issuedate) {
        this.issuedate = issuedate;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "memberid=" + memberid +
                ", membername='" + membername + '\'' +
                ", memberemail='" + memberemail + '\'' +
                ", phone_no=" + phone_no +
                ", issuedate=" + issuedate +
                '}';
    }
}

