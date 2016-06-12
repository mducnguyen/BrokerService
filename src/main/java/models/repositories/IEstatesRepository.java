package models.repositories;

import java.util.List;

import models.Broker;
import models.Estate;
import models.repositories.exception.CannotCreateException;

public interface IEstatesRepository extends IRepository
{
    List<Estate> geAllEstates();

    Estate createEstate(Estate estate) throws CannotCreateException;
    
    Estate findEstate(String placesid);
}
