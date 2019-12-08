package com.table.order.foursquare.repository;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.table.order.foursquare.components.FoursquareApiUrlHelper;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.foursquare.model.response.FoundVenueResponse;
import com.table.order.foursquare.model.response.VenueDetailsResponse;

@Repository
public class FoursquareRepository {

    private FoursquareApiUrlHelper foursquareApiUrlHelper;

    public FoursquareRepository(FoursquareApiUrlHelper foursquareApiUrlHelper) {
        this.foursquareApiUrlHelper = foursquareApiUrlHelper;
    }

    public Set<FoundVenue> findFoodVenuesByNameAndCity(String name, String city) {
    	RestTemplate restTemplate = new RestTemplate();
    	
    	return restTemplate.getForObject(
                	FoursquareApiUrlHelper.INITIAL_URL_FOR_SEARCH,
                	FoundVenueResponse.class,
                	foursquareApiUrlHelper.getSearchUrlDataWithFoodCategory(name, city))
    			.getVenues();

    }

    public VenueDetails getVenueDetails(String venueId) {
    	RestTemplate restTemplate = new RestTemplate();
    	
        return restTemplate.getForObject(
                	FoursquareApiUrlHelper.INITIAL_URL_FOR_DETAILS,
                	VenueDetailsResponse.class,
                	foursquareApiUrlHelper.getDetailsUrlData(venueId))
        		.getVenueDetails();

    }
}
