package org.acme.hibernate.orm.panache;

import java.util.List;
import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.TestReactiveTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import org.assertj.core.api.Assertions;

@QuarkusTest
public class ExamplePanacheTest {

	@Test
	@RunOnVertxContext
	public void test(UniAsserter asserter) {
		Uni<List<Fruit>> listAllUni = Fruit.<Fruit>listAll();
		Fruit mandarino = new Fruit( "Mandarino" );
		asserter.assertThat(
				() -> Panache.withTransaction( () -> Fruit.persist( mandarino ).replaceWith( listAllUni ) ),
				result -> {
					Assertions.assertThat( result ).hasSize( 4 );
					Assertions.assertThat( result ).contains( mandarino );
				}
		);
	}

	private void printThread(String step) {
		System.out.println( step + " - " + Thread.currentThread().getId() + ":" + Thread.currentThread().getName() );
	}
}
