package com.table.order.common.place.finder.service;

import com.table.order.common.place.finder.model.Venue;
import com.table.order.common.place.finder.model.froursquare.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VenueServiceTest {

    Item item;
    ResponseFromFoursquare responseFromFoursquare;

    @InjectMocks
    VenueService venueService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @BeforeEach
    void setUp() {
        item = new Item(new VenueDTO("fdsibchwdp2342", "Pizza Place", new LocationDTO(
                "example address",
                "55.555",
                "22.222",
                "example city",
                "example state",
                "example country")));

        List itemList = new ArrayList<Item>();
        itemList.add(item);
        Group group = new Group();
        group.setItems(itemList);
        List groupList = new ArrayList<Group>();
        groupList.add(group);

        responseFromFoursquare = new ResponseFromFoursquare(new Response(groupList));
    }

    @Test
    void getVenueFromItemTest() {
        Venue venue = venueService.getVenueFromItem(item);

        assertEquals("fdsibchwdp2342", venue.getApiId());
        assertEquals("Pizza Place", venue.getName());
        assertEquals("example address", venue.getLocation().getAddress());
        assertEquals("example city", venue.getLocation().getCity());
        assertEquals("example state", venue.getLocation().getState());
        assertEquals("example country", venue.getLocation().getCountry());
    }

    @Test
    void getVenuesFromResponseTest() {
        List<Venue> venues = venueService.getVenuesFromResponse(responseFromFoursquare);

        assertEquals("fdsibchwdp2342", venues.get(0).getApiId());
        assertEquals("Pizza Place", venues.get(0).getName());
        assertEquals("example address", venues.get(0).getLocation().getAddress());
        assertEquals("example city", venues.get(0).getLocation().getCity());
        assertEquals("example state", venues.get(0).getLocation().getState());
        assertEquals("example country", venues.get(0).getLocation().getCountry());
    }

    //TODO searchVenuesTest
}