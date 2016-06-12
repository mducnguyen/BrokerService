package handlers.responses;

import java.util.List;

import models.Estate;

public class EstateResponse
{
    private String id;
    
    private String place;
    
    private String owner;
    
    private int value;
    
    private List<Integer> rent;
    
    private List<Integer> cost;
    
    private int houses;
    
    private String visit;
    
    private String hypocredit;

    public EstateResponse(String id, String place, String owner, int value,
            List<Integer> rent, List<Integer> cost, int houses, String visit,
            String hypocredit) {
        this.id = id;
        this.place = place;
        this.owner = owner;
        this.value = value;
        this.rent = rent;
        this.cost = cost;
        this.houses = houses;
        this.visit = visit;
        this.hypocredit = hypocredit;
    }

    public EstateResponse(Estate estate) {
        this.id = estate.getId();
        this.place = estate.getPlace();
        this.owner = estate.getOwner();
        this.value = estate.getValue();
        this.rent = estate.getRent();
        this.cost = estate.getCost();
        this.houses = estate.getHouses();
        this.visit = estate.getVisit();
        this.hypocredit = estate.getHypocredit();
    }
}
