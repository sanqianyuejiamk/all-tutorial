package com.example.cruddemo.controller;

import com.example.cruddemo.dto.BookDto;
import com.example.cruddemo.dto.BookResponseDto;
import com.example.cruddemo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 创建图书
     * POST /api/books
     */
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookDto bookDto) {
        BookResponseDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    /**
     * 根据ID获取图书
     * GET /api/books/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    /**
     * 获取所有图书
     * GET /api/books
     */
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * 根据作者获取图书
     * GET /api/books/author/{author}
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(@PathVariable String author) {
        List<BookResponseDto> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    /**
     * 根据书名搜索图书
     * GET /api/books/search?title={title}
     */
    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchBooksByTitle(@RequestParam String title) {
        List<BookResponseDto> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    /**
     * 更新图书
     * PUT /api/books/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDto bookDto) {
        BookResponseDto updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * 删除图书
     * DELETE /api/books/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

