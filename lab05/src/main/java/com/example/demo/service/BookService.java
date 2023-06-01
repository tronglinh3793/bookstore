package com.example.demo.service;

import com.example.demo.entity.BookEntity;
import com.example.demo.reponsitory.IBookReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private IBookReponsitory bookReponsitory;

    public List<BookEntity> getAllBooks(){
        return bookReponsitory.findAll();
    }

    public BookEntity getBookById(Long id){
        return bookReponsitory.findById(id).orElse(null);
    }

    public void addOrUpdateBook(BookEntity book){
        bookReponsitory.save(book);
    }
    public void deleteBook(Long id){
        bookReponsitory.deleteById(id);
    }
}
