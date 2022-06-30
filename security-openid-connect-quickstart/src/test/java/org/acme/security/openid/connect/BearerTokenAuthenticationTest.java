package org.acme.security.openid.connect;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.keycloak.client.KeycloakTestClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BearerTokenAuthenticationTest {

    KeycloakTestClient keycloakClient = new KeycloakTestClient();

    @Test
    public void testAdminAccess() {
        String accessToken = getAccessToken( "admin" );
        for ( int i = 0; i < 100; i++ ) {
            System.out.println("Request " + i);
            RestAssured.given().auth().oauth2( accessToken )
                    .when().get("/api/admin")
                    .then()
                    .statusCode(200);
            System.out.println("Request " + i +": Done!");
        }
    }

    protected String getAccessToken(String userName) {
        return keycloakClient.getAccessToken(userName);
    }
}
