package models;

import java.util.List;

public class Estate
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

    public Estate(String id, String place, String owner, int value,
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

    public String getId()
    {
        return id;
    }

    public String getPlace()
    {
        return place;
    }

    public String getOwner()
    {
        return owner;
    }

    public int getValue()
    {
        return value;
    }

    public List<Integer> getRent()
    {
        return rent;
    }

    public List<Integer> getCost()
    {
        return cost;
    }

    public int getHouses()
    {
        return houses;
    }

    public String getVisit()
    {
        return visit;
    }

    public String getHypocredit()
    {
        return hypocredit;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void updateOwner(String updatedOwner)
    {
        if(!updatedOwner.equals(""))
            this.owner = updatedOwner;
    }

    public void setHypocredit(String hypocredit)
    {
        this.hypocredit = hypocredit;
    }

    public void setVisit(String visit)
    {
        this.visit = visit;
    }
}
