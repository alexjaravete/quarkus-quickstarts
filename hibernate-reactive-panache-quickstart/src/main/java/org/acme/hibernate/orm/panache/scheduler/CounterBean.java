package org.acme.hibernate.orm.panache.scheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;
import org.acme.hibernate.orm.panache.repository.FruitRepository;

@ApplicationScoped
public class CounterBean {

    @Inject
    FruitRepository fruitRepository;

    @Scheduled(every = "2s")
    Uni<Void> scheduleRefresh() {
        return fruitRepository.listAll().replaceWithVoid();
    }

}