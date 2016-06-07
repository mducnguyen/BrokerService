package handlers.responses;

import lombok.Data;
import models.Broker;

/**
 * Created by minhnguy on 01.06.2016.
 */
@Data
public class BrokerResponse {

    private static final String BASE_PATH = "/brokers";

    private String id;

    private String game;

    private String estates;

    public BrokerResponse(String id, String game, String estates) {
        this.id = id;
        this.game = game;
        this.estates = estates;
    }

    public BrokerResponse(Broker broker) {
        this.id = BASE_PATH+"/" +broker.getId();
        this.game = broker.getGame();
        this.estates = broker.getEstates();
    }
}
