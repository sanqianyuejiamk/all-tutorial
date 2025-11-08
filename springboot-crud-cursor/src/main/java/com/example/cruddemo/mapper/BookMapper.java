package com.example.cruddemo.mapper;

import com.example.cruddemo.dto.BookDto;
import com.example.cruddemo.dto.BookResponseDto;
import com.example.cruddemo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    /**
     * BookDto 转 Book 实体
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book toEntity(BookDto bookDto);

    /**
     * Book 实体转 BookResponseDto
     */
    BookResponseDto toResponseDto(Book book);

    /**
     * Book 实体列表转 BookResponseDto 列表
     */
    List<BookResponseDto> toResponseDtoList(List<Book> books);
}

