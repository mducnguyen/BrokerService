package models.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Broker;
import models.Estate;
import models.repositories.exception.CannotCreateException;


public class EstatesRepository implements IEstatesRepository
{
    private Map<String, Estate> estatesMap;

    public EstatesRepository(Map<String, Estate> estatesMap) {
        this.estatesMap = estatesMap;
    }
    
    @Override
    public List<Estate> geAllEstates()
    {
        List<Estate> estateList = new ArrayList<>();

        for (Map.Entry<String, Estate> entry : this.estatesMap.entrySet()){
            estateList.add(entry.getValue());
        }

        return estateList;
    }
    
    @Override
    public Estate createEstate(Estate estate) throws CannotCreateException {
        if (estatesMap.containsKey(estate.getId())) {
            throw new CannotCreateException();
        }
        estatesMap.put(estate.getId(),estate);
        return estate;
    }

    @Override
    public Estate findEstate(String placesid)
    {
        //System.out.println("placesid: " + placesid + estatesMap.get(placesid).getId());
        return estatesMap.get(placesid);
    }
}
