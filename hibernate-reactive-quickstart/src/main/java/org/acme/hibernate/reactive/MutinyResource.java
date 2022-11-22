package org.acme.hibernate.reactive;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.smallrye.mutiny.Uni;
import org.acme.hibernate.reactive.EntityModel.Organization;
import org.acme.hibernate.reactive.EntityModel.Person;

import org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

@Path("organizations")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class MutinyResource {

	@Inject
	SessionFactory sf;

	@GET
	public Uni<List<Organization>> getAll() {
		return sf.withSession( s -> s
				.createQuery(
						"From Organization o left join fetch o.members left join fetch o.createdBy",
						Organization.class
				)
				.getResultList()
		);
	}

	@POST
	@Path("/createPerson")
	public Uni<Person> createPerson() {
		Person person = new Person();
		person.setName( "Dwight Schrute: " + System.nanoTime() );
		return sf.withTransaction( session -> session.persist( person ) ).replaceWith( person );
	}

	@POST
	@Path("/createOrganization")
	public Uni<Organization> createOrganization() {
		var organization = new Organization();
		organization.setName( "Dunder Mifflin Paper Company, Inc.: " + System.nanoTime() );

		return sf.withTransaction( session -> session
						.find( Person.class, 1L )
						.chain( person -> {
							organization.setCreatedBy( person );
							organization.addMember( person );
							return session.persist( organization ).replaceWith( organization );
						} )
		);
	}
}
