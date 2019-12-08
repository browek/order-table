package com.table.order.foursquare.repository;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.table.order.common.place.finder.service.FoursquareApiComponent;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.foursquare.model.response.FoundVenueResponse;
import com.table.order.foursquare.model.response.VenueDetailsResponse;

@Repository
public class FoursquareRepository {

	private FoursquareApiComponent foursquareApiComponent;

	public FoursquareRepository(FoursquareApiComponent foursquareApiComponent) {
		this.foursquareApiComponent = foursquareApiComponent;
	}

	public Set<FoundVenue> findFoodVenuesByNameAndCity(String name, String city) {
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(
					foursquareApiComponent.getSearchUrlByCategoryFoodFormat(), 
					FoundVenueResponse.class,
					foursquareApiComponent.getSearchUrlDataWithFoodCategory(name, city)
				).getVenues();
	}

	public VenueDetails getVenueDetails(String venueId) {
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(
				foursquareApiComponent.getDetailsUrlFormat(), 
				VenueDetailsResponse.class,
				foursquareApiComponent.getDetailsUrlData(venueId)
			).getVenueDetails();
	}
}
