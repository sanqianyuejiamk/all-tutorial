package com.mengka.springboot.jpa;

import com.mengka.springboot.jpa.model.Instructor;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mengka
 * @Date 2023-01-05 16:46
 */
public class SpringRestClient {

    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/instructors/{id}";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/instructors/{id}";

    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/instructors";
    private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/instructors/{id}";

    @Test
    public void updateInstructor_01(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        Instructor updatedEmployee = new Instructor("admin123", "admin123", "admin123@gmail.com");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedEmployee, params);
    }

    @Test
    public void deleteInstructor_01(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }

    @Test
    public void postInstructor_01(){
        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);// set `content-type` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));// set `accept` header

        // request body parameters
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("firstName", "Spring Boot 101");
        map.put("lastName", "A powerful tool for building web apps.");
        map.put("email", "xf123@gmail.com");

//        Map<String, Object> detailMap = new HashMap<>();
//        detailMap.put("id",103L);
//        detailMap.put("youtubeChannel","111");
//        detailMap.put("hobby","222");
//        map.put("instructorDetail", detailMap);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        // send POST request
        ResponseEntity<Instructor> response = restTemplate.postForEntity(CREATE_EMPLOYEE_ENDPOINT_URL, entity, Instructor.class);

        // check response
        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
    }

    @Test
    public void getInstructorById_01(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

        RestTemplate restTemplate = new RestTemplate();
        Instructor updateInstructor = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Instructor.class, params);

        System.out.println(updateInstructor);

        updateInstructor.setEmail("mengka@163.com");
        updateInstructor.getInstructorDetail().setYoutubeChannel("123456789");
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updateInstructor, params);
    }
}
