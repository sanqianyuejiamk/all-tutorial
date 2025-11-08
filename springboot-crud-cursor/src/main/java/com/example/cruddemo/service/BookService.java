package com.example.cruddemo.service;

import com.example.cruddemo.dto.BookDto;
import com.example.cruddemo.dto.BookResponseDto;
import com.example.cruddemo.entity.Book;
import com.example.cruddemo.exception.ResourceNotFoundException;
import com.example.cruddemo.exception.DuplicateIsbnException;
import com.example.cruddemo.mapper.BookMapper;
import com.example.cruddemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * 创建图书
     */
    public BookResponseDto createBook(BookDto bookDto) {
        // 如果提供了ISBN，检查是否已存在
        if (bookDto.getIsbn() != null && !bookDto.getIsbn().trim().isEmpty()) {
            if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
                throw new DuplicateIsbnException("ISBN已存在: " + bookDto.getIsbn());
            }
        }

        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    /**
     * 根据ID获取图书
     */
    @Transactional(readOnly = true)
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("图书不存在，ID: " + id));
        return bookMapper.toResponseDto(book);
    }

    /**
     * 获取所有图书
     */
    @Transactional(readOnly = true)
    public List<BookResponseDto> getAllBooks() {
        return bookMapper.toResponseDtoList(bookRepository.findAll());
    }

    /**
     * 根据作者获取图书
     */
    @Transactional(readOnly = true)
    public List<BookResponseDto> getBooksByAuthor(String author) {
        return bookMapper.toResponseDtoList(bookRepository.findByAuthor(author));
    }

    /**
     * 根据书名搜索图书
     */
    @Transactional(readOnly = true)
    public List<BookResponseDto> searchBooksByTitle(String title) {
        return bookMapper.toResponseDtoList(bookRepository.findByTitleContaining(title));
    }

    /**
     * 更新图书
     */
    public BookResponseDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("图书不存在，ID: " + id));

        // 如果ISBN有变化，检查新ISBN是否已存在
        if (bookDto.getIsbn() != null && !bookDto.getIsbn().trim().isEmpty()) {
            if (!bookDto.getIsbn().equals(book.getIsbn()) && 
                bookRepository.existsByIsbn(bookDto.getIsbn())) {
                throw new DuplicateIsbnException("ISBN已存在: " + bookDto.getIsbn());
            }
        }

        // 更新实体字段
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishDate(bookDto.getPublishDate());

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(updatedBook);
    }

    /**
     * 删除图书
     */
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("图书不存在，ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}

