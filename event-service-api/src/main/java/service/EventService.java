package service;

import model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(Event event);

    Event updateEvent(Event event);

    Optional<Event> getEvent(Integer eventId);

    void deleteEvent(Integer eventId);

    List<Event> getAllEvents();

    Page<Event> getAllEventsByTitle(String title, Pageable pageable);
}
