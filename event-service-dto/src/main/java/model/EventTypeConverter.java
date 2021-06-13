package model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, String> {
    @Override
    public String convertToDatabaseColumn(EventType eventType) {
        return eventType.name();
    }

    @Override
    public EventType convertToEntityAttribute(String eventTypeStr) {
        return EventType.valueOf(eventTypeStr);
    }
}
