package com.order.sample.Domain.SeedWork.Geo;

import com.order.sample.Domain.SeedWork.Base.ValueObject;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Embeddable
@MappedSuperclass
public class Address implements ValueObject {

    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "city")
    private CityName city;
    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;

    public Address() {
    }
    public Address(@NonNull String addressLine1, @NonNull CityName city,
                   @NonNull Country country) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public CityName getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setCity(CityName city) {
        this.city = city;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
