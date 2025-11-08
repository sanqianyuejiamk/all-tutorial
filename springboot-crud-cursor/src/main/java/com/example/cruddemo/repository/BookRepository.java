package com.example.cruddemo.repository;

import com.example.cruddemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    /**
     * 根据ISBN查找图书
     */
    Optional<Book> findByIsbn(String isbn);
    
    /**
     * 检查ISBN是否存在
     */
    boolean existsByIsbn(String isbn);
    
    /**
     * 根据作者查找图书
     */
    java.util.List<Book> findByAuthor(String author);
    
    /**
     * 根据书名查找图书（模糊查询）
     */
    java.util.List<Book> findByTitleContaining(String title);
}

