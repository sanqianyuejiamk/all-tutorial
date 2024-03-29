package com.howtodoinjava;

import com.howtodoinjava.model.Employee;
import com.howtodoinjava.service.EmployeeService;
import javax.cache.Cache;
import javax.cache.CacheManager;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  https://howtodoinjava.com/spring-boot/spring-boot-ehcache-example/
 */
@Log
@SpringBootApplication
public class App implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private EmployeeService employeeService;

  @Override
  public void run(String... args) throws Exception {

    //This will hit the database
    employeeService.getEmployeeById(1L);

    //This will hit the cache - verify the message in console output
    employeeService.getEmployeeById(1L);

    Cache cache = cacheManager.getCache("employeeCache");
    cache.put(3L, new Employee(3L, "Hello", "Hello"));
    Employee value = (Employee)cache.get(3L);
    log.info("----------, value = "+value);
  }
}
