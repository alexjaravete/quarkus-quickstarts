package org.acme.hibernate.orm.panache.scheduler;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;
import org.acme.hibernate.orm.panache.entity.Fruit;
import org.acme.hibernate.orm.panache.repository.FruitRepository;

@ApplicationScoped
public class CounterBean {

    @Scheduled(every = "2s")
    public Uni<Void> scheduleRefresh() {
        System.out.println("Calling the scheduler");
        return Fruit.<Fruit>listAll().invoke( this::printSize ).replaceWithVoid();
    }

    private void printSize(List<Fruit> list) {
        System.out.println( list.size() );
    }

}