package id.gdplabs.olympia.pts.util.converter;

import id.gdplabs.olympia.pts.value.DiscountType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Deny Prasetyo
 * 22 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


@Converter(autoApply = true)
public class DiscountTypeConverter implements AttributeConverter<DiscountType, String> {

    @Override
    public String convertToDatabaseColumn(DiscountType attribute) {
        switch (attribute) {
            case VALUE:
                return "VALUE";
            case PERCENTAGE:
                return "PERCENTAGE";
            default:
                throw new IllegalArgumentException("Unknown " + attribute);
        }
    }

    @Override
    public DiscountType convertToEntityAttribute(String data) {
        switch (data.toUpperCase()) {
            case "VALUE":
                return DiscountType.VALUE;
            case "PERCENTAGE":
                return DiscountType.PERCENTAGE;
            default:
                throw new IllegalArgumentException("Unknown " + data);
        }
    }
}
