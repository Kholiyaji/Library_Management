package com.saurabh.LibraryManagement.model;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="book_issue")
public class BookIssueEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long issueid;

     @ManyToOne
     @JoinColumn(name="bookid")
     private BookEntity bookid;
     @ManyToOne
     @JoinColumn(name="memberid")
     private MemberEntity memberid;

     private LocalDate issuedate;
     private LocalDate duedate;
    private LocalDate returndate;
    private Status status;

    public Long getIssueid() {
        return issueid;
    }

    public void setIssueid(Long issueid) {
        this.issueid = issueid;
    }

    public BookEntity getBookid() {
        return bookid;
    }

    public void setBookid(BookEntity bookid) {
        this.bookid = bookid;
    }

    public MemberEntity getMemberid() {
        return memberid;
    }

    public void setMemberid(MemberEntity memberid) {
        this.memberid = memberid;
    }

    public LocalDate getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(LocalDate issuedate) {
        this.issuedate = issuedate;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public LocalDate getReturndate() {
        return returndate;
    }

    public void setReturndate(LocalDate returndate) {
        this.returndate = returndate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
