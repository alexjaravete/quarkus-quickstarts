package org.acme.hibernate.reactive;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class ArtistEndpointTest {

    @Test
    public void testListAllArtist() {
        //List all, should have all 3 fruits the database has initially:
        given()
			.when()
				.get("/artists")
			.then()
				.statusCode(200)
				.body( equalTo("[" +
									   "{\"id\":1,\"name\":\"Artemisia Gentileschi\",\"paintings\":[{\"id\":1,\"name\":\"Self-Portrait as the Allegory of Painting\"}]}," +
									   "{\"id\":2,\"name\":\"Clara Peters\",\"paintings\":[]}," +
									   "{\"id\":3,\"name\":\"Gwen John\",\"paintings\":[]}]" ) );
    }
}
