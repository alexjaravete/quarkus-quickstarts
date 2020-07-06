package org.acme.hibernate.reactive;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.hibernate.reactive.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static io.vertx.core.http.HttpMethod.*;

@ApplicationScoped
@RouteBase(path = "/fruits", produces = "application/json")
public class FruitsRoutes {

    @Inject
    ObjectMapper mapper;

    @Inject
    Stage.SessionFactory factory;

    private String toJSON(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Route(methods = GET, path = "/")
    public void getAll(RoutingContext rc) throws Exception {
        verify(rc,
                factory.withSession(session ->
                    session.createNamedQuery(Fruit.FIND_ALL)
                    .getResultList()
                    .thenAccept(fruits -> httpResponse(rc)
                            .end(toJSON(fruits)))
                )
        );
    }

    @Route(methods = GET, path = "/:id")
    public void getSingle(RoutingContext rc) {
        final Integer id = Integer.valueOf(rc.request().getParam("id"));

        verify(rc,
                factory.withSession(session ->
                    session.find(Fruit.class, id)
                        .thenAccept(fruit -> httpResponse(rc)
                            .end(toJSON(fruit)))
                )
        );
    }

    @Route(methods = POST, path = "/")
    public void create(RoutingContext rc) {
        final String name = rc.getBodyAsJson().getString("name");
        final Fruit entity = new Fruit(name);

        verify(rc,
                factory.withSession(session ->
                    session.persist(entity)
                        .thenCompose(s -> session.flush())
                            .thenAccept(ignore -> httpResponse(rc)
                                .setStatusCode(201)
                                .end(toJSON(entity)))
                )
        );
    }

    @Route(methods = PUT, path = "/:id")
    public void update(RoutingContext rc) {
        final Integer id = Integer.valueOf(rc.request().getParam("id"));
        final String name = rc.getBodyAsJson().getString("name");

        verify(rc,
                factory.withSession(session ->
                    session.find(Fruit.class, id)
                        .thenCompose(entity -> {
                            entity.setName(name);
                                 return session.flush()
                                    .thenAccept(json -> httpResponse(rc).end(toJSON(entity)));
                        })
                )
        );
    }

    @Route(methods = DELETE, path = "/:id")
    public void delete(RoutingContext rc) {
        final Integer id = Integer.valueOf(rc.request().getParam("id"));

        verify(rc,
                factory.withSession(session ->
                    session.find(Fruit.class, id)
                        .thenCompose(entity -> session.remove(entity))
                        .thenCompose(s -> s.flush())
                        .thenAccept(ignore -> httpResponse(rc)
                            .setStatusCode(204)
                            .end())
                )
        );
    }

    protected static void verify(RoutingContext rc, CompletionStage<?> cs) {
        cs.whenComplete((res, err) -> {
            if (err != null) {
                rc.fail(err.getCause());
            }
        });
    }

    protected static HttpServerResponse httpResponse(RoutingContext rc) {
        return rc.response().putHeader("Content-Type", "application/json");
    }
}
