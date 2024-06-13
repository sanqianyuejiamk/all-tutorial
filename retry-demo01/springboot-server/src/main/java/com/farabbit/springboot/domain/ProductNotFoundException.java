package com.farabbit.springboot.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mengka
 * @Date 2022-03-25 21:29
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long productId) {
        super("Couldn't find product '" + productId + "'.");
    }
}
