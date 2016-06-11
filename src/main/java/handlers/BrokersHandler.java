package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import handlers.requestForm.BrokerForm;
import handlers.responses.BrokerResponse;
import handlers.responses.BrokersResponse;
import handlers.responses.EstatesResponse;
import models.Broker;
import models.repositories.IBrokersRepository;
import models.repositories.exception.CannotCreateException;

import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class BrokersHandler {

    private IBrokersRepository brokersRepository;

    public BrokersHandler(IBrokersRepository brokersRepository) {
        this.brokersRepository = brokersRepository;
    }

    public BrokersResponse getAllBroker(Request request, Response response) {

        List<Broker> brokerList = brokersRepository.geAllBrokers();
        List<String> brokersIds = new ArrayList<>();

        for (Broker broker : brokerList) {
            brokersIds.add(broker.getId());
        }

        response.status(HttpStatus.SC_OK);

        return new BrokersResponse(brokersIds);
    }

    public BrokerResponse createBroker(Request request, Response response) {

        BrokerForm brokerForm;

        try {

            brokerForm = (new ObjectMapper()).readValue(request.body(), BrokerForm.class);
            Broker broker = new Broker(brokerForm.getGame(), brokerForm.getEstates());

            try {
                brokersRepository.createBroker(broker);
                response.status(HttpStatus.SC_CREATED);
                return new BrokerResponse(broker);
            } catch (CannotCreateException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            response.status(HttpStatus.SC_BAD_REQUEST);
        }


        return null;
    }

    public BrokerResponse findBroker(String gameid, Request request, Response response)
    {
        Broker broker = brokersRepository.findBroker(gameid);

        if (broker == null) {
            response.status(HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            BrokerResponse brokerResponse = new BrokerResponse(broker);
            response.status(HttpStatus.SC_OK);
            return brokerResponse;
        }
    }

    public BrokerResponse createBroker(String gameid, Request request, Response response)
    {
        BrokerForm brokerForm;

        try {
            brokerForm = new ObjectMapper().readValue(request.body(), BrokerForm.class);
            Broker broker = new Broker(brokerForm.getGame(), brokerForm.getEstates());
            brokersRepository.createBroker(broker);
            //broker.setBrokerController(new BrokerController(broker.getGame(), brokerForm.getEstates()));
            response.status(HttpStatus.SC_CREATED);
            return new BrokerResponse(broker);
        } catch (IOException e) {
            response.status(HttpStatus.SC_BAD_REQUEST);
            e.printStackTrace();
        } catch (CannotCreateException e) {
            response.status(HttpStatus.SC_CONFLICT);
            e.printStackTrace();
        }
        return null;
    }

    public EstatesResponse getEstates(String gameid, Request request, Response response)
    {
        Broker broker = brokersRepository.findBroker(gameid);

        if (broker == null) {
            response.status(HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            EstatesResponse estatesResponse = new EstatesResponse(broker);
            response.status(HttpStatus.SC_OK);
            return estatesResponse;
        }
    }
}
