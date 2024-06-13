package com.mengka.springboot.component;

import com.mengka.springboot.domain.Location;
import com.mengka.springboot.domain.User;
import com.mengka.springboot.repository.LocationRepository;
import com.mengka.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author mengka
 * @Date 2022-01-27 15:02
 */
@Order(value = 3)
@Component
@Slf4j
public class UserAppStartupRunner implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Location location = new Location();
        location.setPlace("Pune");
        location.setDescriprion("Pune is great place to live");
        location.setLongitude(40.5);
        location.setLatitude(30.6);
        locationRepository.save(location);

        User user1 = new User();
        user1.setFirstName("hyy");
        user1.setLastName("mengka");
        user1.setEmail("mengka@gmail.com");
        user1.setPassword("secret");
        user1.setLocation(location);
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Cena");
        user2.setEmail("john@gmail.com");
        user2.setPassword("secret");
        user2.setLocation(location);
        userRepository.save(user2);
    }
}
