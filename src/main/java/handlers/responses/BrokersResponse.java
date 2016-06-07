package handlers.responses;

import lombok.Data;

import java.util.List;

/**
 * Created by minhnguy on 01.06.2016.
 */
@Data
public class BrokersResponse {

    private List<String> brokers;

    public BrokersResponse(List<String> brokersIds) {
        this.brokers = brokersIds;
    }
}
