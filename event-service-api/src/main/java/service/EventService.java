package service;

import model.Event;

import java.util.List;

public interface EventService {
    void createEvent(Event event);

    void updateEvent(Event event);

    void getEvent(Integer eventId);

    Boolean deleteEvent(Integer eventId);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);
}
