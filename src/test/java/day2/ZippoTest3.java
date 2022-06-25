package day2;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.hamcrest.Matchers.hasSize;

public class ZippoTest3 {


    private ResponseSpecification responseSpecification; //then() den sonraki islemler icin
    private RequestSpecification requestSpecification; // given() den sonraki islemler icin


    // baseURI = http://api.zippopotam.us"
    // Envirenmont veraible gibi dusunelim.
    @BeforeClass
    public void setup() {
        baseURI = "http://api.zippopotam.us"; //static rest asuusre'dan geliyor
       responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY).build();

       requestSpecification= new RequestSpecBuilder()
               .log(LogDetail.BODY)
               .setAccept(ContentType.JSON) //postmanda body raw json yaptıgimiz islem.
               .build();
    }


    @Test
    public void checkPlacesArraySize() {

        given()
                .log().uri()
                .when()
                .get("/us/90210") // http ile başlamadığını görünce baseURI'yi bulup getiriyor.
                .then()
                .statusCode(200)
                .body("places", hasSize(1))
                .log().body()
                .contentType(ContentType.JSON)
        ;
    }

    // yaptigimiz testler ortak ise response specification

    @Test
    public void checkPlacesArraySizeResponseSpecification() {

        given()
                .log().uri()
                .when()
                .get("/us/90210") // http ile başlamadığını görünce baseURI'yi bulup getiriyor.
                .then()
                .body("places", hasSize(1))
                .spec(responseSpecification)
        ;
    }

    @Test
    public void checkPlacesArraySizeRequestSpecification() {

        given()
                .spec(requestSpecification)
                .when()
                .get("/us/90210") // http ile başlamadığını görünce baseURI'yi bulup getiriyor.
                .then()
                .body("places", hasSize(1))
                .spec(responseSpecification)
        ;
    }
}
