package com.mengka.springboot.service;

import com.mengka.springboot.domain.User;
import com.mengka.springboot.domain.UserLocationDTO;
import com.mengka.springboot.repository.UserRepository;
import com.mengka.springboot.transfor.BookTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author mengka
 * @Date 2022-01-27 14:55
 */
@Service
public class UserService {

    private final BookTransfer bookTransfer = BookTransfer.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    public List<UserLocationDTO> getAllUsersBook(){
       List<User> users = userRepository.findAll();

        return null;
    }

    public List<UserLocationDTO> getAllUsersLocation(){
        return userRepository.findAll()
                .stream()
                //.map(this::convertEntitytoDto)
                .map(bookTransfer::toDTO)
                .collect(Collectors.toList());
    }

    public User findById(){
        Optional<User> optional = userRepository.findById(1L);
        return optional.get();
    }


    private UserLocationDTO convertEntitytoDto(User user){
        UserLocationDTO userLocationDTO = new UserLocationDTO();
        userLocationDTO.setUserId(user.getId());
        userLocationDTO.setEmail(user.getEmail());
        userLocationDTO.setPlace(user.getLocation().getPlace());
        userLocationDTO.setLongitude(user.getLocation().getLongitude());
        userLocationDTO.setLatitude(user.getLocation().getLatitude());
        return userLocationDTO;
    }
}
