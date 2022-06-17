package org.acme.hibernate.orm.panache;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;

@QuarkusTest
public class ExamplePanacheTest {

	@Test
	public void test() {
		Fruit mandarino = new Fruit( "Mandarino" );
		Panache.withTransaction( () -> Fruit.persist( mandarino ) ).await().indefinitely();
		List<Fruit> result = Fruit.<Fruit>listAll().await().indefinitely();
		Assertions.assertThat( result ).hasSize( 4 );
		Assertions.assertThat( result ).contains( mandarino );
	}
}
