package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shapesecurity.salvation2.Values.Hash;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testGetUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        String body = RestAssured.given().when().get("/users").then().extract().body().asString();

        System.out.println(body);
    }

    @Test
    public void testGetJanet() {
        RestAssured.baseURI = "https://reqres.in/api";

        RestAssured.given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data[1].first_name", org.hamcrest.Matchers.equalTo("Janet"));
    }

    @Test
    public void testPostSteve() {
        RestAssured.baseURI = "https://reqres.in/api";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Steve");
        map.put("job", "CEO");

        RestAssured.given()
            .body(map.toString())
        .when()
            .post("/users")
        .then()
            .statusCode(201);
    }
}
