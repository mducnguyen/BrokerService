import handlers.BrokersHandler;
//import handlers.EstatesHandler;
import models.repositories.BrokersRepository;
import models.repositories.IBrokersRepository;
import models.repositories.EstatesRepository;
import models.repositories.IEstatesRepository;
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
        //EstatesHandler estatesHandler = new EstatesHandler((IEstatesRepository) RepositoryProvider.provide(IEstatesRepository.class));
        get("/brokers", (request, response) -> brokersHandler.getAllBroker(request, response), jsonTransformer);
        post("/brokers", (request, response) -> brokersHandler.createBroker(request, response), jsonTransformer);

        get("/brokers/:gameId", (request, response) -> brokersHandler.findBroker(request.params(":gameId"), request,response), jsonTransformer );
        put("/brokers/:gameId", (request, response) -> brokersHandler.updateBroker(request.params(":gameId"), request,response),jsonTransformer);
        
        get("/brokers/:gameId/places", (request, response) -> brokersHandler.getEstates(request.params(":gameId"), request,response), jsonTransformer );
        
        get("/brokers/:gameId/places/:placesId", (request, response) -> brokersHandler.findEstate(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
        put("/brokers/:gameId/places/:placesId", (request, response) -> brokersHandler.createEstate(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
        
        get("/brokers/:gameId/places/:placesId/owner", (request, response) -> brokersHandler.getOwner(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
        post("/brokers/:gameId/places/:placesId/owner", (request, response) -> brokersHandler.insertOwner(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
        put("/brokers/:gameId/places/:placesId/owner", (request, response) -> brokersHandler.changeOwner(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
    
        put("/brokers/:gameId/places/:placesId/hypothecarycredit", (request, response) -> brokersHandler.addHypothecarycredit(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
        delete("/brokers/:gameId/places/:placesId/hypothecarycredit", (request, response) -> brokersHandler.removeHypothecarycredit(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
    
        post("/brokers/:gameId/places/:placesId/visit", (request, response) -> brokersHandler.insertVisit(request.params(":gameId"), request.params(":placesId"), request,response), jsonTransformer );
    }

    private static void registerRepositories() {
        RepositoryProvider.register(IBrokersRepository.class, new BrokersRepository(new HashMap<>()));
    }
}

