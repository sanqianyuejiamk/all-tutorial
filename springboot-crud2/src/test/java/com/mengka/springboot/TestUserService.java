package com.mengka.springboot;

import com.mengka.springboot.domain.User;
import com.mengka.springboot.repository.UserRepository;
import com.mengka.springboot.service.UserService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static org.junit.Assert.assertEquals;

/**
 * @author mengka
 * @Date 2022-01-28 15:31
 */
public class TestUserService extends BaseTest{

    @Mock
    private UserService userService;

    @Spy
    private UserRepository userRepository;

    @Test
    public void test_findById(){
        //Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(getUser()));
        //Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(getUser()));
        Mockito.when(userService.findById()).thenReturn(getUser());

        User user = userService.findById();
        assertEquals("mengka", user.getLastName());
    }

    private User getUser(){
        User user = new User();
        user.setId(1L);
        user.setEmail("mengka@163.com");
        user.setFirstName("hyy");
        user.setLastName("mengka");
        user.setPassword("123");
        return user;
    }
}
