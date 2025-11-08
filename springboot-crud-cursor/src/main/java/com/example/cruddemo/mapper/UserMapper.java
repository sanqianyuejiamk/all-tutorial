package com.example.cruddemo.mapper;

import com.example.cruddemo.dto.UserDto;
import com.example.cruddemo.dto.UserResponseDto;
import com.example.cruddemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * UserDto 转 User 实体
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserDto userDto);

    /**
     * User 实体转 UserResponseDto
     */
    UserResponseDto toResponseDto(User user);

    /**
     * User 实体列表转 UserResponseDto 列表
     */
    List<UserResponseDto> toResponseDtoList(List<User> users);
}

