package com.order.sample.Presentation.Rest.Request;

import com.order.sample.Domain.RecipientAddress;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RecipientAddressReq implements Serializable {
    @NotEmpty
    private String name;
    @NotEmpty
    private String addressLine1;
    @NotNull
    private CityName city;
    @NotNull
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public CityName getCity() {
        return city;
    }

    public void setCity(CityName city) {
        this.city = city;
    }
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    public RecipientAddress toDomainModel(){
        return new RecipientAddress(getName(),getAddressLine1(),getCity(),getCountry());
    }
}
