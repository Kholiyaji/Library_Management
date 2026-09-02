package com.saurabh.LibraryManagement.controller;

import com.saurabh.LibraryManagement.model.BookIssueEntity;
import com.saurabh.LibraryManagement.service.BookissueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookissue")
public class BookIssueController {

    @Autowired
    private BookissueService bookissueservice;
    @PostMapping("/issue/{bookid}/{memberid}")
    public BookIssueEntity issueBook(
            @PathVariable("bookid") Long bookid,
            @PathVariable("memberid") Long memberid) {

        return bookissueservice.issueBook(bookid, memberid);
    }
    @PutMapping("/return/{issueid}")
    public BookIssueEntity returnBook(@PathVariable Long issueid){
        return bookissueservice.returnBook(issueid);
    }
    @GetMapping("issuedbooks")
    public List<BookIssueEntity> issuedBooks(){
        return bookissueservice.viewBooks();
    }



}
