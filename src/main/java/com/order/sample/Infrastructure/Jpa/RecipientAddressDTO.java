package com.order.sample.Infrastructure.Jpa;

import com.order.sample.Domain.SeedWork.Geo.Address;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RecipientAddressDTO extends Address {
    private String name;


    public RecipientAddressDTO() {
    }
    public RecipientAddressDTO(@NonNull String name, @NonNull String addressLine1,
                            @NonNull CityName city, @NonNull Country country) {
        super(addressLine1, city, country);
        this.name = Objects.requireNonNull(name, "name must not be null");
    }


}
