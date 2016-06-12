package models;

import models.repositories.EstatesRepository;
import models.repositories.IEstatesRepository;
import models.repositories.IRepository;
import models.repositories.provider.RepositoryProvider;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class Broker {

    private String id;

    private String game;

    private String estates;
    
    //private IEstatesRepository estatesRepository;

    public Broker(String game, String estates) {
        this.game = game;
        this.estates = estates;
        String[] strArr = game.split("/");
        this.id = strArr[strArr.length - 1];
        //this.estatesRepository = (IEstatesRepository) RepositoryProvider.provide(IEstatesRepository.class);
    }

    public String getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getEstates() {
        return estates;
    }
    
//    public EstatesRepository getEstatesRepository() {
//        return (EstatesRepository) estatesRepository;
//    }

    public void updateBroker(Broker updatedBroker)
    {
        if (updatedBroker != null) {

            if (updatedBroker.getGame() != null) {
                game = updatedBroker.getGame();
            }
            if (updatedBroker.getEstates() != null) {
                estates = updatedBroker.getEstates();
            }
        }
    }
}
