package com.saurabh.LibraryManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name="Book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;
    private String booktitle;
    private String authorname;
    private String isbn;
    private String genre;
    private int totalcopies;
    private int availablecopies;


    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getTotalcopies() {
        return totalcopies;
    }

    public void setTotalcopies(int totalcopies) {
        this.totalcopies = totalcopies;
    }

    public int getAvailablecopies() {
        return availablecopies;
    }

    public void setAvailablecopies(int availablecopies) {
        this.availablecopies = availablecopies;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookid=" + bookid +
                ", booktitle='" + booktitle + '\'' +
                ", authorname='" + authorname + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", totalcopies=" + totalcopies +
                ", availablecopies=" + availablecopies +
                '}';
    }
}
