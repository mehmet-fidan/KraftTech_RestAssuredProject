package day2;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ZipponTest {


    @Test
    public void tes1() {

        //  Buradaki metotlar birbirine bagli metotlar

        given()  //setup baslangic ayarlari

                .when() // get, put, post, patch, delete metodlarini burda kullanacagiz

                .then()

        ; // testleri burada kullaniyoruz.

    }

    //"http://api.zippopotam.us/us/90210"

    @Test
    public void statusCodeTest() {

        given()
                .when().get("http://api.zippopotam.us/us/90210")
                .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void contentTypeTest() {

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
             // .contentType("application/json")
        ;
    }

    @Test
    public void logTest(){

        given()
                .log().all()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().all()
                //.log().body()
                ;
    }

    @Test
    public void checkCountry(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("country",equalTo("United States"))
                .log().body()
                ;
    }
    @Test
    public void checkPlacesState(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("places[0].state",equalTo("California"))
                .log().body()
                ;
    }
    @Test
    public void checkPlacesPlaceName(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("places[0].'place name'",equalTo("Beverly Hills")) //arasinda bosluk olan keylerde tek tirnak kullaniyoruz.
                .log().body()
        ;
    }
   //places arrayinin size test edelim

    @Test
    public void checkPlacesArraySize(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("places",hasSize(1))
        ;
    }

    @Test
    public void multiBodyTest(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("places",hasSize(1))
                .body("places[0].'place name'",equalTo("Beverly Hills"))
                .body("places[0].state",equalTo("California"))
                .log().body()
                ;
    }

    @Test
    public void stateAbbreviationTest(){
        given()
                .when().get("http://api.zippopotam.us/us/90210")
                .then()
                .statusCode(200)
                .body("places[0].'state abbreviation'",equalTo("CA"))
                .log().body()
                ;
    }
}
