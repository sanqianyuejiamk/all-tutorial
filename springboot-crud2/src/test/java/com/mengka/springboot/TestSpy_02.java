package com.mengka.springboot;

import com.mengka.springboot.domain.User;
import com.mengka.springboot.repository.UserRepository;
import com.mengka.springboot.service.JerryService;
import com.mengka.springboot.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;

/**
 * @author mengka
 * @Date 2022-01-28 16:25
 */
public class TestSpy_02 extends BaseTest{

    @Mock
    private UserService userService;

    //@Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private JerryService jerryService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        given(userService.findById()).willReturn(getUser());
        //Mockito.when(userService.findById()).thenReturn(getUser());
    }

    @Test
    public void test_goHome(){
        //Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(getUser()));
        //Mockito.when(userService.findById()).thenReturn(getUser());

        String result = jerryService.goHome();
        assertEquals("mengka@163.com", result);
    }

    @Test
    public void callRealMethodTest() {
        doCallRealMethod().when(jerryService).goHome();
        doCallRealMethod().when(jerryService).doSomeThingB();
        Mockito.when(userService.findById()).thenReturn(getUser());

        jerryService.goHome();

        verify(jerryService).doSomeThingA();
        verify(jerryService).doSomeThingB();
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
