package impl;

import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.EventRepository;
import service.EventService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) throws NoSuchElementException {
        LOGGER.info("Create event {}", event.getTitle());
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) throws NoSuchElementException {
        LOGGER.info("Update event {}", event.getId());
        Event eventTemp = eventRepository.getOne(event.getId());
        eventTemp.setTitle(event.getTitle());
        eventTemp.setPlace(event.getPlace());
        eventTemp.setSpeaker(event.getSpeaker());
        eventTemp.setDateTime(event.getDateTime());
        eventTemp.setEventType(event.getEventType());
        return eventRepository.save(eventTemp);
    }

    @Override
    public Optional<Event> getEvent(Integer eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public void deleteEvent(Integer eventId) throws NoSuchElementException {
        LOGGER.info("Delete Event {}", eventId);
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Page<Event> getAllEventsByTitle(String title, Pageable pageable) {
        return eventRepository.findByTitle(title, pageable);
    }
}
