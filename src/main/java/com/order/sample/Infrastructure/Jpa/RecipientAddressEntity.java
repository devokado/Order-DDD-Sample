package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.SeedWork.Geo.Address;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class RecipientAddressEntity extends Address {
    @Column(name = "shipping_name")
    private String name;

    @SuppressWarnings("unused") // Used by JPA only.
    public RecipientAddressEntity() {
    }
    public RecipientAddressEntity(@NonNull String name, @NonNull String addressLine1,
                                  @NonNull CityName city, @NonNull Country country) {
        super(addressLine1, city, country);
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public String getName() {
        return name;
    }



}
