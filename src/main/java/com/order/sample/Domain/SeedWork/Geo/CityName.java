package com.order.sample.Domain.SeedWork.Geo;

import com.fasterxml.jackson.annotation.JsonValue;
import com.order.sample.Domain.SeedWork.Base.ValueObject;
import lombok.NonNull;

import java.util.Objects;

public class CityName implements ValueObject {

    private final String name;

    public CityName(@NonNull  String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityName cityName = (CityName) o;
        return Objects.equals(name, cityName.name);
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
