package com.example.demo.reponsitory;

import com.example.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookReponsitory extends JpaRepository<BookEntity, Long> {
}
