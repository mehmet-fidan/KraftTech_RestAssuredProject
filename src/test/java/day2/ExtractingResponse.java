package day2;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ExtractingResponse {

  //    https://reqres.in/api/users



    @BeforeClass
    public void setup(){
        baseURI ="https://reqres.in"; //static rest assured
    }


    // https://reqres.in/api/users
    // George alacagiz bir degisken atayip assertion
    // Body icindeki bir degeri disari cikariyorsun vw bir atama yapiyorsun

    @Test
    public void extractingJsonPath(){

       // String extractValue;

        String extractValue = given()
                .when()
                .get("/api/users")
                .then()
                .log().body()
                .extract().path("data[0].first_name")
                ;

        System.out.println("alinan deger : "+extractValue);
        Assert.assertEquals(extractValue,"George");
    }

    @Test
    public void extractingJsonPathList(){

        // List<String> extractValue;

        List<String> list =given()
                .when()
                .get("/api/users")
                .then()
                .extract().path("data.first_name")
                ;


        Assert.assertTrue(list.contains("George"));
        System.out.println(list);
    }

}
