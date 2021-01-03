package com.order.sample.Domain.SeedWork.Geo.Converter;

import com.order.sample.Domain.SeedWork.Geo.CityName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CityNameConverter implements AttributeConverter<CityName, String> {

    @Override
    public String convertToDatabaseColumn(CityName attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public CityName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new CityName(dbData);
    }
}
