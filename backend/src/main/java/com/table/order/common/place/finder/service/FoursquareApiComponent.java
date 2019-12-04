package com.table.order.common.place.finder.service;

import com.table.order.common.place.finder.model.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FoursquareApiComponent {

    public static final String FOURSQUARE_API_URL = "https://api.foursquare.com/v2/venues";

    @Value("${foursquare.clientId}")
    private String clientId;

    @Value("${foursquare.clientSecret}")
    private String clientSecret;


    public String getFoursquareExploreUrl() {
        return FOURSQUARE_API_URL + "/explore?client_id={clientId}&client_secret={clientSecret}&" + "v={version}&limit={limit}&ll={lat},{lng}&query={query}&" + "fbclid=IwAR3NFUJgyrfv2D4469fepWHwpkAq8ntBpFakzYjciZSXoGkj3gmJuKu5tSM";
    }

    public Map<String, Object> getFoursquareUriVariables(Request request) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("clientId", clientId);
        uriVariables.put("clientSecret", clientSecret);
        uriVariables.put("version", "20180323");
        uriVariables.put("limit", 5);
        uriVariables.put("lat", request.getLat());
        uriVariables.put("lng", request.getLng());
        uriVariables.put("query", request.getQuery());

        return uriVariables;
    }

}
