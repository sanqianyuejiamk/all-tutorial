package com.mengka.springboot.builder_01;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/11/16
 * @since farabbit2.0
 */
public class Taa {

    public static void main(String... args){
        Customer customer = Customer.builder()
                .id(044101331L)
                .firstName("mengka")
                .lastName("hyy")
                .birthdate("2020-05-01")
                .build();

        System.out.println("firstname = "+customer.getFirstName());
    }
}
