package handlers.responses;

import java.util.List;

import models.Broker;

public class EstatesResponse
{
    private List<String> estates;

    public EstatesResponse(List<String> estatesIds) {
        this.estates = estatesIds;
    }
}
