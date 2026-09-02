package com.saurabh.LibraryManagement.service;


import com.saurabh.LibraryManagement.exceptions.ResourceNotFound;
import com.saurabh.LibraryManagement.model.BookEntity;
import com.saurabh.LibraryManagement.model.BookIssueEntity;
import com.saurabh.LibraryManagement.model.MemberEntity;
import com.saurabh.LibraryManagement.repo.BookRepo;
import com.saurabh.LibraryManagement.repo.BookissueRepo;
import com.saurabh.LibraryManagement.repo.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookissueService {

    private static final Logger log= (Logger) LoggerFactory.getLogger(BookissueService.class);

   @Autowired
    private BookissueRepo bookissuerepo;
   @Autowired
    private BookRepo bookrepo;
   @Autowired
    private MemberRepo memberrepo;

   public BookIssueEntity issueBook(Long bookid, Long memberid){
       log.info("Request received to issue book with id: {} to member with id: {}", bookid, memberid);
       BookEntity book=bookrepo.findById(bookid).orElse(null);
       MemberEntity member=memberrepo.findById(memberid).orElse(null);

       if(book==null){
           log.warn("Book with id {} was not found in the database", bookid);
         throw new ResourceNotFound("book not found");
       }
       if(member==null){
           log.warn("Member with id {} was not found in the database", memberid);
          throw new ResourceNotFound("member not found");
       }

       if(book.getAvailablecopies()<=0){
           log.warn("book with id {} was not found in the database", bookid);
           throw new ResourceNotFound("book has no available copies");
       }

       book.setAvailablecopies(book.getAvailablecopies()-1);
       bookrepo.save(book);


       BookIssueEntity bookissue=new  BookIssueEntity();

       bookissue.setBookid(book);
       bookissue.setMemberid(member);

       bookissue.setIssuedate(LocalDate.now());

       bookissue.setDuedate(LocalDate.now().plusDays(7));
       bookissue.setReturndate(null);

       log.info("book issued successfully");
       return bookissuerepo.save(bookissue);

   }
   @GetMapping("/returnbook/{issued}")
   public BookIssueEntity returnBook(Long issueid){
       log.info("Request received to return issued book with issue id: {}", issueid);
       BookIssueEntity bookissue=bookissuerepo.findById(issueid).orElse(null);
       if(bookissue==null){

           log.info("no issued book ");
           throw new ResourceNotFound("book issue not found");
       }
       BookEntity book=bookissue.getBookid();

       bookissue.setReturndate(LocalDate.now());
       book.setAvailablecopies(book.getAvailablecopies()+1);
       bookrepo.save(book);
       return bookissue;
   }
  public List<BookIssueEntity> viewBooks(){

       List<BookIssueEntity> allBooks=bookissuerepo.findAll();

       List<BookIssueEntity> issuedBooks=new ArrayList<>();
       for(BookIssueEntity issue:allBooks){
           if (issue.getReturndate() != null) {
               log.warn("Book issue with id  has already been returned");
               throw new ResourceNotFound("Book already returned");
           }
           if(issue.getReturndate()==null){
               issuedBooks.add(issue);
           }
       }
       return issuedBooks;
  }

}
