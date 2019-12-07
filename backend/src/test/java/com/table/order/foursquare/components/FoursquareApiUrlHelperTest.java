package com.table.order.foursquare.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FoursquareApiUrlHelperTest {

    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_SECRET = "clientSecret";
    private static final String VERSION = "version";
    private static final String FOOD_CATEGORY_ID = "foodCategoryId";
    private static final int LIMIT = 50;

    private FoursquareApiUrlHelper foursquareApiUrlHelper;

    @BeforeEach
    void setUp() {
        foursquareApiUrlHelper = new FoursquareApiUrlHelper(CLIENT_ID, CLIENT_SECRET, VERSION, FOOD_CATEGORY_ID, LIMIT);

    }

    @Test
    void getSearchUrlDataWithFoodCategory_correctData_returnCorrectMap() {
        Map<String, String> searchUrlDataWithFoodCategory
                = foursquareApiUrlHelper.getSearchUrlDataWithFoodCategory("query", "near");

        searchUrlDataWithFoodCategory
                .values()
                .stream()
                .allMatch(value -> equalsOneOfProperty(value));
    }

    private boolean equalsOneOfProperty(String value) {
        return  CLIENT_ID.equals(value)             ||
                CLIENT_SECRET.equals(value)         ||
                VERSION.equals(value)               ||
                FOOD_CATEGORY_ID.equals(value)      ||
                String.valueOf(LIMIT).equals(value);
    }


}