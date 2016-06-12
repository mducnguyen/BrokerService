package handlers.requestForm;

import java.util.List;

public class EstateForm
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
}
