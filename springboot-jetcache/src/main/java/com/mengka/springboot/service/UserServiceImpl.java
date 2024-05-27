/**
 * Created on 2018/8/11.
 */
package com.mengka.springboot.service;

import com.mengka.springboot.domain.User;
import com.mengka.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Slf4j
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUser(long userId) {
        log.info("load user: " + userId);
        Optional<User> optional = userRepository.findById(userId);
        User user = optional.isPresent()?optional.get():null;
        return user;
    }

    @Override
    public User updateUser(User user) {
        userRepository.updateUserNameById(user.getName(),user.getId());
        return user;
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
