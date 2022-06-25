package day2;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ZippoTest2 {


    @Test
    public void queryParameterTest() {

        int page = 4;
        given()
                // .param("page", page)
                .queryParam("page", page)
                .log().uri()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .statusCode(200)
                .body("data[0].name",equalTo("Ahilya Kaniyar"))
                .log().body()
        ;
    }

    @Test
    public void queryParameterTestMulti() {

        // int page = 3;

        HashMap<String, Object> params = new HashMap();
        params.put("page",5);
        params.put("id","3434");

        for (int page = 1; page <= 10; page++) {

            given()
                   // .param("page", page)
                   // .queryParam("page", page)
                   // .queryParam("id", "123")
                  .queryParams("page",page,"id", "12")
                  .queryParams(params)
                    .log().uri()
                    .when()
                    .get("https://gorest.co.in/public-api/users")
                    .then()
                    .statusCode(200)
                    .body("meta.pagination.page", equalTo(page))
                   // .log().body()
            ;
        }
    }
}
