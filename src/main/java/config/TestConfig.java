package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeSuite;

public class TestConfig {

    public static RequestSpecification requestSpec;

    @BeforeSuite
    public void setup(){
        //RestAssured.proxy("localhost", 8888);

        requestSpec = new RequestSpecBuilder()
                              .setBaseUri("http://localhost")
                              .setPort(9200)
                              .setBasePath("/")
                              .addHeader("Content-Type", "application/json")
                              .build().log().all();

        RestAssured.requestSpecification = requestSpec;
    }
}
