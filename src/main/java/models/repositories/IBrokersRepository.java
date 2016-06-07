package models.repositories;

import models.Broker;
import models.repositories.exception.CannotCreateException;

import java.util.List;

/**
 * Created by minhnguy on 01.06.2016.
 */
public interface IBrokersRepository extends IRepository {
    List<Broker> geAllBrokers();

    Broker createBroker(Broker broker) throws CannotCreateException;

}
