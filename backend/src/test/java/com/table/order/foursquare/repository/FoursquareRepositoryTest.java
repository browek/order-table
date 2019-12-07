package com.table.order.foursquare.repository;

import com.table.order.foursquare.components.FoursquareApiUrlHelper;
import com.table.order.foursquare.model.FoundVenue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoursquareRepositoryTest {

    @Test
    void findFoodVenuesByNameAndCity() {
        FoursquareApiUrlHelper mock = mock(FoursquareApiUrlHelper.class);

        HashMap<String, String> urlData = new HashMap<>();
        urlData.put("client_id", "5YS0CLWR2Y0FLLYHRXTYJTEUBPMGGEBYDWHAIF5VDSUGCFET");
        urlData.put("client_secret", "3IMWKNFDRYP0DR2IBAAFOUV5TJ0FFARXVVJHNNOTNRFGPKNS");
        urlData.put("near", "Rzeszów");
        urlData.put("category_id", "4d4b7105d754a06374d81259");
        urlData.put("query", "chilita");
        urlData.put("version", "20180323");
        urlData.put("limit", String.valueOf(50));

        when(mock.getSearchUrlDataWithFoodCategory("chilita", "Rzeszów"))
                .thenReturn(urlData);


        FoursquareRepository foursquareRepository = new FoursquareRepository(mock);
        Set<FoundVenue> foodVenuesByNameAndCity = foursquareRepository.findFoodVenuesByNameAndCity("chilita", "Rzeszów");
    }
}