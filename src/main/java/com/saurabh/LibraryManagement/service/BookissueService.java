package com.saurabh.LibraryManagement.service;


import com.saurabh.LibraryManagement.model.BookEntity;
import com.saurabh.LibraryManagement.model.BookIssueEntity;
import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.repo.BookRepo;
import com.saurabh.LibraryManagement.repo.BookissueRepo;
import com.saurabh.LibraryManagement.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookissueService {
   @Autowired
    private BookissueRepo bookissurepo;
   @Autowired
    private BookRepo bookrepo;
   @Autowired
    private MemberRepo memberrepo;

   public BookIssueEntity issueBook(Long bookid, Long memberid){

       BookEntity book=bookrepo.findById(bookid).orElse(null);
       MemberEntity member=memberrepo.findById(memberid).orElse(null);

       if(book==null){
         throw new RuntimeException("book not found");
       }
       if(member==null){
          throw new RuntimeException("member not found");
       }

       if(book.getAvailablecopies()<=0){
           throw new RuntimeException("book has no available copies");
       }

       book.setAvailablecopies(book.getAvailablecopies()-1);
       bookrepo.save(book);


       BookIssueEntity bookissue=new  BookIssueEntity();

       bookissue.setBookid(book);
       bookissue.setMemberid(member);

       bookissue.setIssuedate(LocalDate.now());

       bookissue.setReturndate(LocalDate.now().plusDays(7));

       return bookissurepo.save(bookissue);

   }
   public BookIssueEntity returnBook(Long issueid){
       BookIssueEntity bookissue=bookissurepo.findById(issueid).orElse(null);
       if(bookissue==null){
           throw new RuntimeException("book issue not found");
       }
       BookEntity book=bookissue.getBookid();

       bookissue.setReturndate(LocalDate.now());
       book.setAvailablecopies(book.getAvailablecopies()+1);
        bookrepo.save(book);
       return bookissue;
   }
  public List<BookIssueEntity> viewBooks(){

       List<BookIssueEntity> allBooks=bookissurepo.findAll();

       List<BookIssueEntity> issuedBooks=new ArrayList<>();
       for(BookIssueEntity issue:allBooks){
           if (issue.getReturndate() != null) {
               throw new RuntimeException("Book already returned");
           }
           if(issue.getReturndate()==null){
               issuedBooks.add(issue);
           }
       }
       return issuedBooks;
  }

}
