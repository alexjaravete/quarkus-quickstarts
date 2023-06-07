package org.acme.hibernate.orm.panache;

import java.util.List;

import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("users")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class SysUserResource {

    @GET
    public Uni<List<SysUser>> get() {
        return SysUser.listAll( Sort.by( "username" ) );
    }
}
