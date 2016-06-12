//package handlers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpStatus;
//
//import handlers.responses.BrokerResponse;
//import handlers.responses.EstateResponse;
//import handlers.responses.EstatesResponse;
//import models.Broker;
//import models.Estate;
//import models.repositories.EstatesRepository;
//import models.repositories.IEstatesRepository;
//import spark.Request;
//import spark.Response;
//
//public class EstatesHandler
//{
//    private IEstatesRepository estatesRepository;
//
//    public EstatesHandler(IEstatesRepository estatesRepository) {
//        this.estatesRepository = estatesRepository;
//    }
//
//    public EstatesResponse getAllEstates(String gameid, Request request, Response response)
//    {
//        List<Estate> estateList = estatesRepository.geAllEstates();
//        List<String> estatesIds = new ArrayList<>();
//
//        for (Estate estate : estateList) {
//            estatesIds.add(estate.getId());
//        }
//
//        response.status(HttpStatus.SC_OK);
//
//        return new EstatesResponse(estatesIds);
//    }
//
//    public EstateResponse findEstate(String gameid, String placesid, Request request,
//            Response response)
//    {
//        Estate estate = brokerRepository.getBroker(gameid).getEstates.findEstate(placesid);
//        
//        if (estate == null) {
//            response.status(HttpStatus.SC_NOT_FOUND);
//            return null;
//        } else {
//            EstateResponse estateResponse = new EstateResponse(estate);
//            response.status(HttpStatus.SC_OK);
//            return estateResponse;
//        }
//    }
//}
