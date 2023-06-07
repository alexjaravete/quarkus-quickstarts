package org.acme.hibernate.orm.panache;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class SysUserEndpointTest {

    @Test
    public void testListAll() {
        Response response = given()
                .when()
                .get( "/users" )
                .then()
                .statusCode( 200 )
                .contentType( "application/json" )
                .extract().response();
        assertThat( response.jsonPath().getList( "username" ) ).containsExactlyInAnyOrder( "DavideD" );
    }
}
