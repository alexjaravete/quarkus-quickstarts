package org.acme.security.openid.connect;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import org.jboss.logging.Logger;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class RolesAugmentor implements SecurityIdentityAugmentor {

    private static final Logger log = Logger.getLogger(RolesAugmentor.class);

    private static final String EMAIL_CLAIM_NAME = "https://popagile.com/email";

    @Inject
    Mutiny.SessionFactory factory;

    @Override
    public int priority() {
        return SecurityIdentityAugmentor.super.priority();
    }

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }


        log.debug("Looking up user in the database.");

        // The actual details here don't matter. Any database request will
        // trigger the 4 and out behavior. So I changed it to run count(*) to make it simpler.
        return count()
                .map( item -> {
                          QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder()
                                  .setPrincipal( identity.getPrincipal() )
                                  .addAttributes( identity.getAttributes() )
                                  .addCredentials( identity.getCredentials() )
                                  .addRoles( identity.getRoles() );

                          List.of( "admin" ).stream().forEach( role -> builder.addRole( role ) );

                          return builder.build();
                      }
                );
    }

    private Uni<Long> count() {
//        return factory.withSession( this::count );
        return PopUser.count();
    }

    private Uni<Long> count(Mutiny.Session session) {
        return session
                .createQuery( "select count(u) from PopUser u", Long.class )
                .getSingleResult();
    }
}
