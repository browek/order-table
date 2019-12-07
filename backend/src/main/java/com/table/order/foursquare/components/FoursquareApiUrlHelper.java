package com.table.order.foursquare.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FoursquareApiUrlHelper {

    public static final String INITIAL_URL_FOR_SEARCH =
            "https://api.foursquare.com/v2/venues/search" +
                    "?client_id={client_id}" +
                    "&client_secret={client_secret}" +
                    "&near={near}" +
                    "&categoryId={category_id}" +
                    "&query={query}" +
                    "&limit={limit}" +
                    "&v={version}";

//    https://api.foursquare.com/v2/venues/4d57f09436d1721eddc6f0b1?client_id=5YS0CLWR2Y0FLLYHRXTYJTEUBPMGGEBYDWHAIF5VDSUGCFET&client_secret=3IMWKNFDRYP0DR2IBAAFOUV5TJ0FFARXVVJHNNOTNRFGPKNS&v=20180323

    public static final String INITIAL_URL_FOR_DETAILS =
            "https://api.foursquare.com/v2/venues/{venue_id}" +
                    "?client_id={client_id}" +
                    "&client_secret={client_secret}" +
                    "&v={version}";

    private String clientId;
    private String clientSecret;
    private String version;
    private String foodCategoryId;
    private int limit;

    public FoursquareApiUrlHelper(@Value("${foursquare.clientId}") String clientId,
                                  @Value("${foursquare.clientSecret}") String clientSecret,
                                  @Value("${foursquare.version}") String version,
                                  @Value("${foursquare.category.food.id}") String foodCategoryId,
                                  @Value("${foursquare.result.limit}") int limit) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.version = version;
        this.foodCategoryId = foodCategoryId;
        this.limit = limit;
    }

    public Map<String, String> getSearchUrlDataWithFoodCategory(String query, String near) {
        HashMap<String, String> urlData = new HashMap<>();
        urlData.put("client_id", clientId);
        urlData.put("client_secret", clientSecret);
        urlData.put("near", near);
        urlData.put("category_id", foodCategoryId);
        urlData.put("query", query);
        urlData.put("version", version);
        urlData.put("limit", String.valueOf(limit));

        return urlData;
    }

    public Map<String, String> getDetailsUrlData(String venueId) {
        HashMap<String, String> urlData = new HashMap<>();
        urlData.put("client_id", clientId);
        urlData.put("client_secret", clientSecret);
        urlData.put("venue_id", venueId);
        urlData.put("version", version);

        return urlData;
    }

}