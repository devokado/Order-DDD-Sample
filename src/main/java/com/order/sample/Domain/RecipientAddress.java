package com.order.sample.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.sample.Domain.SeedWork.Geo.Address;
import com.order.sample.Domain.SeedWork.Geo.CityName;
import com.order.sample.Domain.SeedWork.Geo.Country;
import org.springframework.lang.NonNull;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/** @Entity annotation over a class defines that, it has a distinct separate existence.
 *  Thus we can run DB queries, without being dependent on any other class.
 *  @Embeddable annotation over a class defines that, it does not have independent existence.
 *  Thus we cannot run DB queries, without depending on other class.
 */
@Embeddable
public class RecipientAddress extends Address {

    @Column(name = "recipient_name")
    private String name;

    public RecipientAddress() {
    }
    public RecipientAddress(@NonNull String name, @NonNull String addressLine1,
                            @NonNull CityName city, @NonNull Country country) {
        super(addressLine1, city, country);
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    @NonNull
    @JsonProperty("name")
    public String name() {
        return name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RecipientAddress that = (RecipientAddress) o;
        return Objects.equals(name, that.name);
    }
}
