package model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, String> {
    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr);
    }
}