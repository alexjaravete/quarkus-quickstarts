package org.acme.hibernate.orm.panache;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.text.IsEmptyString.emptyString;

@QuarkusTest
public class ItemsEndpointTest {

    @Test
    public void testListItems() {
        //List all, should have all 3 fruits the database has initially:
        Response response = given()
                .when()
                .get( "/items/testCreation" )
                .then()
                .statusCode( 200 )
                .contentType( "application/json" )
                .extract().response();
    }
}
