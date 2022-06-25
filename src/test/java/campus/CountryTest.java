package campus;

import campus.pojo.Country;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CountryTest {

    // Cookies cookies;
    Response response;
    String userID;

    @BeforeClass
    public void login() {

        baseURI = "https://demo.mersys.io";

        //  {
        //    "username", "richfield.edu";
        //    "password", "Richfield2020!";
        //   "rememberMe", "true";
        //  }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "richfield.edu");
        credentials.put("password", "Richfield2020!");
        credentials.put("rememberMe", "true");

        //
        response = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response()
        ;

        Cookies cookies = response.detailedCookies();
        System.out.println(cookies);
    }

    @Test
    public void createCountry() {

        String randomName = RandomStringUtils.randomAlphabetic(6);
        String randomCode = RandomStringUtils.randomAlphabetic(2);

        Country country = new Country();
        country.setName(randomName);
        country.setCode(randomCode);
        System.out.println(country);


        userID = given()
                .cookies(response.getDetailedCookies())
                .body(country)
                .contentType(ContentType.JSON)
                .when()
                .post("/school-service/api/countries")
                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getString("id")
        ;

        System.out.println("user id : " + userID);
    }

    @Test(dependsOnMethods = "createCountry")
    public void updateCountry() {

        Country country2 = new Country();
        country2.setId(userID);

        country2.setName(RandomStringUtils.randomAlphabetic(7));
        country2.setCode(RandomStringUtils.randomAlphabetic(2));

        Response response1 = given()

                .cookies(response.getDetailedCookies())
                .body(country2)
                .contentType(ContentType.JSON)
                .when()
                .put("/school-service/api/countries")
                .then()
                .statusCode(200)
                .body("name", equalTo(country2.getName()))
                .body("code", equalTo(country2.getCode()))
                .extract().response();

        System.out.println(response1.jsonPath().getString("name"));
        System.out.println(response1.jsonPath().getString("code"));
    }

    @Test(priority = 2, dependsOnMethods = "updateCountry")
    public void deleteCountry(){

        given()
                .cookies(response.getDetailedCookies())
                .when()
                .delete("/school-service/api/countries/"+userID)
                .then()
                .statusCode(200)
                .contentType(emptyOrNullString());
                ;

    }
}
