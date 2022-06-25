package day4Post;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestGetUser {


    int userId;
    int userID;

    @Test
    public void createUser() {

        // token: Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb

     //  String name = getRandomName();
       String email = getRandomEmail();

        Map<String,String> user = new LinkedHashMap<>();
     //   user.put("name",name);
        user.put("name",getRandomName());
        user.put("gender", "male");
        user.put("email",email);
        user.put("status","active");

       userId= given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .contentType(ContentType.JSON)
               // .body("{\"name\":\""+getRandomName"\",\"gender\":\"male\",\"email\":\""+getRandomEmail()+"\",\"status\":\"active\"}")
                .body(user)
                .when()
                .post("https://gorest.co.in/public-api/users")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code",equalTo(201))
              //.body("data.name",equalTo(name))
                .body("data.name",equalTo(user.get("name")))
                .extract().jsonPath().getInt("data.id")
        ;

        System.out.println("user id: "+ userId);
    }


    private String getRandomEmail() {
        String userEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT)+"@gmail.com";
        return userEmail;
    }

    private String getRandomName() {
        String userName = RandomStringUtils.randomAlphabetic(6).toLowerCase(Locale.ROOT);
        return userName;
    }

    @Test(dependsOnMethods = "createUser")
    public void updateUserById(){
        given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .contentType(ContentType.JSON)
                .pathParam("userId",userId)
                .body("{\"name\":\"omer\",\"gender\":\"male\",\"email\":\""+getRandomEmail()+"\",\"status\":\"active\"}")
                .when()
                .put("https://gorest.co.in/public-api/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.name",equalTo("omer"))
                .log().body()
        ;
    }

    @Test(dependsOnMethods = "createUser",priority=1)
    public void deleteById(){

        given()
                .header("Authorization", "Bearer 6a72f07ad4685b1a298a2615c2a4683c5513b67a62991ac4f3e56fa1ebd113cb")
                .contentType(ContentType.JSON)
                .pathParam("userId",userId)
                .when()
                .delete("https://gorest.co.in/public-api/users/{userId}")
                .then()
                .statusCode(200)
                .log().body()
                ;
    }
}
