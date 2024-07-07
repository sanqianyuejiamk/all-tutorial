package com.mengka.threaddemo;

import com.mengka.threaddemo.domain.Foo;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author mengka
 * @Date 2024-07-07 19:15
 */
public class ApplicationLiveTest {

    @Test
    public void givenUserWithReadPrivilegeAndHasPermission_whenGetFooById_thenOK() {
        final Response response = givenAuth("john", "123").get("http://localhost:8082/foos/1");
        assertEquals(200, response.getStatusCode());
        String aa = response.asString();
        assertTrue(response.asString().contains("id"));
    }

    @Test
    public void givenUserWithNoWritePrivilegeAndHasPermission_whenPostFoo_thenForbidden() {
        final Response response = givenAuth("john", "123").contentType(MediaType.APPLICATION_JSON_VALUE).body(new Foo("sample")).post("http://localhost:8082/foos");
        assertEquals(403, response.getStatusCode());
    }

    @Test
    public void givenUserWithWritePrivilegeAndHasPermission_whenPostFoo_thenOk() {
        final Response response = givenAuth("tom", "111").and().body(new Foo("sample")).and().contentType(MediaType.APPLICATION_JSON_VALUE).post("http://localhost:8082/foos");
        assertEquals(201, response.getStatusCode());
        assertTrue(response.asString().contains("id"));
    }

    @Test
    public void givenUserMemberInOrganization_whenGetOrganization_thenOK() {
        final Response response = givenAuth("john", "123").get("http://localhost:8082/organizations/1");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("id"));
    }

    @Test
    public void givenUserMemberNotInOrganization_whenGetOrganization_thenForbidden() {
        final Response response = givenAuth("john", "123").get("http://localhost:8082/organizations/2");
        assertEquals(403, response.getStatusCode());
    }

    @Test
    public void givenDisabledSecurityExpression_whenGetFooByName_thenError() {
        Response response = givenAuth("john", "123").get("http://localhost:8082/foos?name=sample");
        assertEquals(500, response.getStatusCode());
    }

    private RequestSpecification givenAuth(String username, String password) {
        return RestAssured.given().log().uri().auth().form(username, password, new FormAuthConfig("/login","username","password"));
    }
}
