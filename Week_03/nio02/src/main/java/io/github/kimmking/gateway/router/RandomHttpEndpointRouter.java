package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {

    Random random = new Random();

    @Override
    public String route(List<String> endpoints) {
        return endpoints.get(random.nextInt(endpoints.size()));
    }
}
