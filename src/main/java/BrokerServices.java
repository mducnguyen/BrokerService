import handlers.BrokersHandler;
import models.repositories.BrokersRepository;
import models.repositories.IBrokersRepository;
import models.repositories.provider.RepositoryProvider;
import transformer.JsonTransformer;

import java.util.HashMap;

import static spark.Spark.*;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class BrokerServices {

    public static void main(String args[]) {

        registerRepositories();
        JsonTransformer jsonTransformer = new JsonTransformer();

        BrokersHandler brokersHandler = new BrokersHandler((IBrokersRepository) RepositoryProvider.provide(IBrokersRepository.class));
        get("/brokers", (request, response) -> brokersHandler.getAllBroker(request, response), jsonTransformer);
        post("/brokers", (request, response) -> brokersHandler.createBroker(request, response), jsonTransformer);

        get("/brokers/:gameId", (request, response) -> brokersHandler.findBroker(request.params(":gameId"), request,response), jsonTransformer );
        put("/brokers/:gameId", (request, response) -> brokersHandler.createBroker(request.params(":gameId"), request,response),jsonTransformer);
        
        get("/brokers/:gameId/places", (request, response) -> brokersHandler.getEstates(request.params(":gameId"), request,response), jsonTransformer );
    }

    private static void registerRepositories() {
        RepositoryProvider.register(IBrokersRepository.class, new BrokersRepository(new HashMap<>()));
    }
}

