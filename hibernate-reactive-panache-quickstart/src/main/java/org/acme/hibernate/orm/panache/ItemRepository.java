package org.acme.hibernate.orm.panache;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

	public Uni<List<Item>> findByProductIds(Set<Long> productIds) {
		return find( "productId IN (:pids)", Parameters.with( "pids", productIds ) )
				.list();
	}

	public Uni<List<Long>> findExistingProductIds(Set<Long> productIds) {
		return find( "select productId from Item where productId IN (:pids)", Parameters.with( "pids", productIds ) )
				.project( Long.class )
				.list();
	}
}
