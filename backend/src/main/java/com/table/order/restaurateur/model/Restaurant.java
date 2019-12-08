package com.table.order.restaurateur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.Activated;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.security.model.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;



@Entity
@Data
@Where(clause = "active='1'")
public class Restaurant implements Activated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "api_id")
    private String apiId;

    private String name;

    private String city;

    private String street;

    @NotNull
    @ManyToOne(optional = false)
    @JsonIgnore
    private User owner;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<ReservationRequest> reservationRequests;

    private boolean active = true;

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean value) {
        this.active = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
