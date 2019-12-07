package com.table.order.foursquare.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VenueLocation {

    private String country;
    private String city;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
