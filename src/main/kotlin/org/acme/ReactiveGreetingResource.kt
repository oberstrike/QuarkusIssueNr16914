package org.acme

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.Entity
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Entity
class GreetingEntity(
    var message: String = "Hello"
) : PanacheEntity()


@ApplicationScoped
class GreetingRepository: PanacheRepository<GreetingEntity>


@Path("/hello-resteasy-reactive")
@ApplicationScoped
class ReactiveGreetingResource {

    @Inject
    lateinit var greetingRepository: GreetingRepository

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): Uni<List<GreetingEntity>> {
        return greetingRepository.findAll().list()
    }
}


