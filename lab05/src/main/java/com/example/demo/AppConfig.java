package com.example.demo;

import com.example.demo.entity.BookEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<BookEntity> getBooks(){
        List<BookEntity> books = new ArrayList<>();
        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Lập trinh web Spring MVC");
        book1.setAuthor("Ánh Nguyễn");
        book1.setPrice(10.99);
        book1.setImage("/img/hinh-1.jpg");
        books.add(book1);

        BookEntity book2 = new BookEntity();
        book2.setId(2L);
        book2.setTitle("Lập trình ứng dụng Spring");
        book2.setAuthor("Huy Cường");
        book2.setPrice(12.99);
        books.add(book2);

        BookEntity book3 = new BookEntity();
        book3.setId(3L);
        book3.setTitle("Lập trình ứng dụng Java");
        book3.setAuthor("Xuân nhân");
        book3.setPrice(15.99);
        books.add(book3);

        BookEntity book4 = new BookEntity();
        book4.setId(4L);
        book4.setTitle("Lập trình ứng dụng Java");
        book4.setAuthor("Đăng Khoa");
        book4.setPrice(15.99);
        books.add(book4);
        return books;
    }
}
