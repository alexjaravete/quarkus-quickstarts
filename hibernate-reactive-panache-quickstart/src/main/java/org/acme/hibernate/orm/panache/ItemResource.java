package org.acme.hibernate.orm.panache;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Path("items")
@ApplicationScoped
public class ItemResource {

	@Inject
	ItemRepository repository;

	@GET
	@Path("testCreation")
	public Multi<Item> createItems() {
		List<Item> items = IntStream.range( 1, 7000 )
				.asLongStream()
				.mapToObj( Item::new )
				.collect( toList() );
		return createAll( items );
	}

	private Multi<Item> createAll(List<Item> items) {
		return Uni.createFrom().item( items )
				.onItem().transformToMulti( Multi.createFrom()::iterable )
				.group().intoLists().of( 700 )
				.onItem().transformToUniAndConcatenate( this::createItems )
				.onItem().disjoint();
	}

	@ReactiveTransactional
	public Uni<List<Item>> createItems(Collection<Item> entities) {
		final Set<Long> pids = entities.stream().map( Item::getProductId ).collect( toSet() );
		return repository
				.findExistingProductIds( pids )
				.map( existingPids -> entities.stream()
						.filter( item -> !existingPids.contains( item.getProductId() ) )
						.collect(toList())
				)
				.call( repository::persist );
	}
}
