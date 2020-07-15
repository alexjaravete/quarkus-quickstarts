package org.acme.hibernate.reactive;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.reactive.mutiny.Mutiny;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("artists")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ArtistMutinyResource {
    private static final Logger LOGGER = Logger.getLogger( ArtistMutinyResource.class.getName());

    @Inject
    Mutiny.Session mutinySession;

    @GET
    public Multi<Artist> getAll() {
        return mutinySession
                .createNativeQuery( Artist.NATIVE_FIND_ALL, Artist.class )
                .getResults();
    }
}
