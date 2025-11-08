package com.example.cruddemo.service;

import com.example.cruddemo.dto.UserDto;
import com.example.cruddemo.dto.UserResponseDto;
import com.example.cruddemo.entity.User;
import com.example.cruddemo.exception.ResourceNotFoundException;
import com.example.cruddemo.exception.DuplicateEmailException;
import com.example.cruddemo.mapper.UserMapper;
import com.example.cruddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * 创建用户
     */
    public UserResponseDto createUser(UserDto userDto) {
        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateEmailException("邮箱已存在: " + userDto.getEmail());
        }

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    /**
     * 根据ID获取用户
     */
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户不存在，ID: " + id));
        return userMapper.toResponseDto(user);
    }

    /**
     * 获取所有用户
     */
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toResponseDtoList(userRepository.findAll());
    }

    /**
     * 更新用户
     */
    public UserResponseDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户不存在，ID: " + id));

        // 如果邮箱有变化，检查新邮箱是否已存在
        if (!user.getEmail().equals(userDto.getEmail()) && 
            userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateEmailException("邮箱已存在: " + userDto.getEmail());
        }

        // 使用 MapStruct 更新实体（只更新非空字段）
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }

    /**
     * 删除用户
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("用户不存在，ID: " + id);
        }
        userRepository.deleteById(id);
    }
}


