package org.acme.panache;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to a Kafka topic (prices). The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    @Channel("quote-requests")
    Emitter<String> quoteRequestEmitter;

    @Outgoing("generated-price")
    Multi<Integer> generate() {
        quoteRequestEmitter.send(  );
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .map(tick -> random.nextInt(100));
    }

}
