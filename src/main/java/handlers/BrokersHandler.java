package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import handlers.requestForm.BrokerForm;
import handlers.requestForm.EstateForm;
import handlers.requestForm.HypocreditForm;
import handlers.requestForm.OwnerForm;
import handlers.requestForm.VisitForm;
import handlers.responses.BrokerResponse;
import handlers.responses.BrokersResponse;
import handlers.responses.EstateResponse;
import handlers.responses.EstateURIResponse;
import handlers.responses.HypocreditResponse;
import handlers.responses.OwnerResponse;
import handlers.responses.VisitResponse;
import models.Broker;
import models.Estate;
import models.repositories.EstatesRepository;
import models.repositories.IBrokersRepository;
import models.repositories.IEstatesRepository;
import models.repositories.exception.CannotCreateException;

import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class BrokersHandler {

    private IBrokersRepository brokersRepository;
    
    private Map<String, IEstatesRepository> estatesMap;
    //private IEstatesRepository estatesRepository;

    public BrokersHandler(IBrokersRepository brokersRepository) {
        this.brokersRepository = brokersRepository;
        estatesMap = new HashMap<String, IEstatesRepository>();
//        for(Broker broker : brokersRepository.geAllBrokers())
//            estatesMap.put(broker.getId(), new EstatesRepository(new HashMap<String, Estate>()));
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
                estatesMap.put(broker.getId(), new EstatesRepository(new HashMap<String, Estate>()));
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

    public BrokerResponse updateBroker(String gameid, Request request, Response response)
    {
        Broker broker = brokersRepository.findBroker(gameid);

        if (broker == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such broker!");
            return null;
        } else {

            try {
                BrokerForm brokerForm = new ObjectMapper().readValue(request.body(), BrokerForm.class);
                Broker updatedBroker = new Broker(brokerForm.getGame(), brokerForm.getEstates());
                broker.updateBroker(updatedBroker);
                response.status(HttpStatus.SC_OK);
                return new BrokerResponse(broker);
            } catch (IOException e) {
                response.status(HttpStatus.SC_BAD_REQUEST);
                e.printStackTrace();
            }

            BrokerResponse responseObj = new BrokerResponse(broker);
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }    
    
    public EstateURIResponse getEstates(String gameid, Request request, Response response)
    {
        Broker broker = brokersRepository.findBroker(gameid);
        
        if (broker == null) {
            response.status(HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            EstateURIResponse estateResponse = new EstateURIResponse(broker.getEstates());
            response.status(HttpStatus.SC_OK);
            return estateResponse;
        }
//        List<Estate> estateList = brokersRepository.findBroker(gameid).getEstatesRepository().geAllEstates();
//        List<String> estatesIds = new ArrayList<>();
//
//        for (Estate estate : estateList) {
//            estatesIds.add(estate.getId());
//        }
//
//        response.status(HttpStatus.SC_OK);
//
//        return new EstatesResponse(estatesIds);
    }

    public EstateResponse findEstate(String gameid, String placesid, Request request,
            Response response)
    {
        //System.out.println("gameid: " + gameid + " placesid: " + placesid);
        Estate estate = estatesMap.get(gameid).findEstate(placesid);
        
        if (estate == null) {
            response.status(HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            EstateResponse estateResponse = new EstateResponse(estate);
            response.status(HttpStatus.SC_OK);
            return estateResponse;
        }
    }

    public EstateResponse createEstate(String gameid, String placesid, Request request,
            Response response)
    {
        EstateForm estateForm;

        try {

            estateForm = (new ObjectMapper()).readValue(request.body(), EstateForm.class);
            Estate estate = new Estate(estateForm.getId(), estateForm.getPlace(), estateForm.getOwner(), estateForm.getValue(),
                    estateForm.getRent(), estateForm.getCost(), estateForm.getHouses(), estateForm.getVisit(), estateForm.getHypocredit());

            try {
//                if(estatesMap.containsKey(estatesMap.get(gameid).findEstate(gameid).getId()))
//                {
//                    response.status(HttpStatus.SC_OK);
//                    return new EstateResponse(estate);
//                }
                estatesMap.get(gameid).createEstate(estate);
                response.status(HttpStatus.SC_CREATED);
                return new EstateResponse(estate);
            } catch (CannotCreateException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            response.status(HttpStatus.SC_BAD_REQUEST);
        }

        return null;
    }

    public OwnerResponse getOwner(String gameid, String placesid, Request request,
            Response response)
    {
        String owner = estatesMap.get(gameid).findEstate(placesid).getOwner();
        
        if (owner == null) {
            response.status(HttpStatus.SC_NOT_FOUND);
            return null;
        } else {
            OwnerResponse ownerResponse = new OwnerResponse(owner);
            response.status(HttpStatus.SC_OK);
            return ownerResponse;
        }
    }

    public OwnerResponse insertOwner(String gameid, String placesid, Request request,
            Response response)
    {
        OwnerForm ownerForm;

        try {

            ownerForm = (new ObjectMapper()).readValue(request.body(), OwnerForm.class);
            Estate estate = estatesMap.get(gameid).findEstate(placesid);

            if(!estate.getOwner().equals(""))
            {
                response.status(HttpStatus.SC_CONFLICT);
                return null;
            }
            estatesMap.get(gameid).findEstate(placesid).setOwner(ownerForm.getOwner());
            response.status(HttpStatus.SC_OK);
            return new OwnerResponse(ownerForm.getOwner());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.status(HttpStatus.SC_BAD_REQUEST);
        }

        return null;
    }

    public OwnerResponse changeOwner(String gameid, String placesid, Request request,
            Response response)
    {        
        Estate estate = estatesMap.get(gameid).findEstate(placesid);

        if (estate == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such Estate!");
            return null;
        } else {

            try {
                OwnerForm ownerForm = (new ObjectMapper()).readValue(request.body(), OwnerForm.class);
                String updatedOwner = ownerForm.getOwner();
                estate.updateOwner(updatedOwner);
                response.status(HttpStatus.SC_OK);
                return new OwnerResponse(ownerForm.getOwner());
            } catch (IOException e) {
                response.status(HttpStatus.SC_BAD_REQUEST);
                e.printStackTrace();
            }

            return null;
        }
    }

    public HypocreditResponse addHypothecarycredit(String gameid, String placesid,
            Request request, Response response)
    {
        HypocreditForm hypocreditForm;

        try {

            hypocreditForm = (new ObjectMapper()).readValue(request.body(), HypocreditForm.class);
            Estate estate = estatesMap.get(gameid).findEstate(placesid);

            if(!estate.getHypocredit().equals(""))
            {
                response.status(HttpStatus.SC_CONFLICT);
                return null;
            }
            estatesMap.get(gameid).findEstate(placesid).setHypocredit(hypocreditForm.getHypocredit());
            response.status(HttpStatus.SC_OK);
            return new HypocreditResponse(estatesMap.get(gameid).findEstate(placesid).getHypocredit());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.status(HttpStatus.SC_BAD_REQUEST);
        }

        return null;
    }

    public String removeHypothecarycredit(String gameid, String placesid,
            Request request, Response response)
    {
        Estate estate = estatesMap.get(gameid).findEstate(placesid);

        if (estate == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such estate!");
            return null;
        }

        if(estate.getHypocredit().equals(""))
        {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no hypocredit!");
            return null;
        }

        estate.setHypocredit("");

        response.status(HttpStatus.SC_OK);
        return "Removed hypocredit!";
    }

    public VisitResponse insertVisit(String gameid, String placesid, Request request,
            Response response)
    {
        VisitForm visitForm;

        try {

            visitForm = (new ObjectMapper()).readValue(request.body(), VisitForm.class);
            Estate estate = estatesMap.get(gameid).findEstate(placesid);

            if(!estate.getVisit().equals(""))
            {
                response.status(HttpStatus.SC_CONFLICT);
                return null;
            }
            estatesMap.get(gameid).findEstate(placesid).setVisit(visitForm.getVisit());
            response.status(HttpStatus.SC_OK);
            return new VisitResponse(visitForm.getVisit());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.status(HttpStatus.SC_BAD_REQUEST);
        }

        return null;
    }
}
