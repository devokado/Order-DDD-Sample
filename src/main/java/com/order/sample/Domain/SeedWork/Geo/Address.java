package com.order.sample.Domain.SeedWork.Geo;

import com.order.sample.Domain.SeedWork.Base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(city, address.city) &&
                country == address.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1,city,country);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addressLine1);
        sb.append(", ");
        sb.append(city);
        sb.append(", ");
        sb.append(country);
        return sb.toString();
    }
}
