package id.gdplabs.olympia.pts.infra.util.converter;

import id.gdplabs.olympia.pts.domain.value.TransactionType;

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
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(TransactionType attribute) {
        switch (attribute) {
            case ALL:
                return "ALL";
            case SERVICE:
                return "SERVICE";
            case SALES:
                return "SALES";
            case OTHER:
                return "OTHER";
            default:
                throw new IllegalArgumentException("Unknown " + attribute);
        }
    }

    @Override
    public TransactionType convertToEntityAttribute(String data) {
        switch (data.toUpperCase()) {
            case "ALL":
                return TransactionType.ALL;
            case "SERVICE":
                return TransactionType.SERVICE;
            case "SALES":
                return TransactionType.SALES;
            case "OTHER":
                return TransactionType.OTHER;
            default:
                throw new IllegalArgumentException("Unknown " + data);
        }
    }
}
