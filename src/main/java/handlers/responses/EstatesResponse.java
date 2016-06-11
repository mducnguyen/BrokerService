package handlers.responses;

import models.Broker;

public class EstatesResponse
{
    private String estates;

    public EstatesResponse(String estates)
    {
        this.estates = estates;
    }
    
    public EstatesResponse(Broker broker)
    {
        this.estates = broker.getEstates();
    }
}
