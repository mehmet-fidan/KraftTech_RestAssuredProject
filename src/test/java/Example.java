import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class Example {


    @Test
    public void getUsers() {

        baseURI= "https://gorest.co.in";

        //request object

        RequestSpecification httpRequest = RestAssured.given();

        //response object

        Response response = httpRequest.when().get("/public-api/users");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        response.then().statusCode(200);
        // status code validation

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,201);
    }

    @Test
    public void createUser(){

        RequestSpecification httpRequest = RestAssured.given();



    }
}
