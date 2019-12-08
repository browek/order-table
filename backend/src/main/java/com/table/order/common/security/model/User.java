package com.table.order.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.Activated;
import com.table.order.common.model.Notification;
import com.table.order.common.model.ReservationRequest;
import com.table.order.restaurateur.model.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Where(clause = "active='1'")
public class User implements Activated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NonNull
    private String username;

    @JsonIgnore
    @NonNull
    private String password;

    private boolean enabled;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_role")
    private Role roles;

    @OneToMany(mappedBy = "owner")
    private Set<Restaurant> restaurants;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<ReservationRequest> sendReservationRequests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receivedUser")
    private Set<Notification> notifications;

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
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}






