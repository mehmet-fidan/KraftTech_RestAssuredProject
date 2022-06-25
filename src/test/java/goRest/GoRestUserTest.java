package goRest;

import goRest.PojoModel.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;

public class GoRestUserTest {


    @Test
    public void goRestGetUsers() {

        List<User> userList = given()

                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                //  .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data", not(empty()))
                .extract().jsonPath().getList("data", User.class);


        System.out.println(userList);
    }

    @Test
    public void responseExample(){

        Response response = given()

                .when()
                .get("https://gorest.co.in/public-api/users")

                .then()
                .log().body()
                .statusCode(200)
                .extract().response()

                ;

        User user = response.jsonPath().getObject("data[1]", User.class);
        System.out.println(user.getName());
        List<User> userList =response.jsonPath().getList("data", User.class);
        int pages = response.jsonPath().getInt("meta.pagination.pages");

       // System.out.println(response.getHeader());

    }

}
