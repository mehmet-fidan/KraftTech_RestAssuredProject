
package zippoMentoring;


import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @Test
    public void test1() {

        given()

                .when()

                .then()
        ;
    }

    @Test
    public void test2() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .log().body()
                .log().status()
        ;

    }

    @Test
    public void test3() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .contentType(ContentType.JSON)
        ;
    }

    @Test
    public void test4() {
        given()
                .log().all()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .contentType(ContentType.JSON)
        ;
    }

    @Test
    public void test5() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("country", equalTo("United States"))
                .body("'post code'", equalTo("90210"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void test6() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places", hasSize(1))
                .body("places.state", hasItem("California"))

        ;
    }

    @Test
    public void test7() {
        String zipCode = "52809";
        String country = "us";

        given()
                .pathParam("country", country)
                .pathParam("zipCode", zipCode)
                .when()
                .get("http://api.zippopotam.us/{country}/{zipCode}")
                .then()
                .log().body()
        ;
    }

    @Test
    public void test8() {
        String zipCode = "01000";
        String country = "TR";

        given()
                .pathParam("country", country)
                .pathParam("zipCode", zipCode)
                .when()
                .get("http://api.zippopotam.us/{country}/{zipCode}")
                .then()
                .log().body()
                .body("places.'place name'", hasItem("Mutlu Köyü"))
        ;
    }

    @Test
    public void test9() {

        String country = "TR";
        for (int i = 80930; i <= 81950; i++) {

            given()
                    .pathParam("country", country)
                    .pathParam("zipCode", i)
                    .when()
                    .get("http://api.zippopotam.us/{country}/{zipCode}")
                    .then()
                    .log().body()
            ;
        }
    }

    @Test
    public void test10() {

        for (int i = 1; i < 10; i++) {

            given()
                    .log().uri()
                    .param("page", i)
                    .when()
                    .get("http://gorest.co.in/public/v2/users")
                    .then()
                    .log().body()
            ;
        }

    }

    @Test
    public void test11() {

        given()
                .log().uri()
                .queryParam("page",1)
                .when()
                .get("http://gorest.co.in/public/v2/users")
                .then()
                .log().body()
        ;
}

}
