package pojoClass;

import org.testng.annotations.Test;
import pojoClass.pojo.Location;
import pojoClass.pojo.Places;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Testing_with_Project {


    @Test
    public void getLocation() {
        // http://api.zippopotam.us/tr/01000
        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                .log().body()
                .statusCode(200)

        ;
    }

    @Test
    public void extractingJsonAsPojo() {

        Location location = given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                //   .log().body()
                .extract().as(Location.class);

        System.out.println(location);
        System.out.println(location.getPostcode());
        System.out.println(location.getCountry());
        System.out.println(location.getPlaces());
        System.out.println(location.getPlaces().get(0).getPlacename());

        for (Places place : location.getPlaces()) {

            if (place.getPlacename().equals("Camuzcu Köyü")) {
                System.out.println(place.getLatitude());
            }

        }
    }

    @Test
    public void extractJsonAsPojoPlaces(){

      List<Places> placesList =
        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                .log().body()
              //  .extract().jsonPath().getList("places",Places.class)
                .extract().jsonPath().getList("places")
                ;
        System.out.println(placesList);

      /*  for (Places places : placesList) {
            System.out.println(places.getState());



        }
         */
    }
}
