import io.swagger.v3.oas.annotations.tags.Tag;
import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/events")
@Tag(name = "Events", description = "The Event API")
public class EventServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceController.class);

    private final EventService eventService;

    @Autowired
    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @Operation(summary = "Find all events")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<Event> getAll() {
        LOGGER.info("GET /events");
        return eventService.getAllEvents().stream()
                .map(event -> new Event(event.getTitle(), event.getPlace(), event.getSpeaker(), event.getDateTime(), event.getEventType()))
                .collect(Collectors.toList());
    }

    @GetMapping
    @Operation(summary = "Lookup All events by a title")
    public Page<Event> getAllEventsByTitle(@PathVariable(value = "title") String title, Pageable pageable,
                                                PagedResourcesAssembler pagedAssembler) {
        LOGGER.info("GET /events/{}", title);
        Page<Event> eventPage = eventService.getAllEventsByTitle(title, pageable);
        List<Event> eventList = new ArrayList<>(eventPage.getContent());
        return new PageImpl<Event>(eventList, pageable, eventPage.getTotalPages());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find event by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Event not found")})
    public Event getEvent(@PathVariable("id") Integer id) {
        LOGGER.info("GET /events/{}", id);
        return eventService.getEvent(id)
                .orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an Event")
    public Event createEvent(@RequestBody @Validated Event event) {
        LOGGER.info("POST /events/{}", event.getTitle());
        return eventService.createEvent(event);
    }

    @PutMapping
    @Operation(summary = "Update an Event")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Event not found")})
    public Event updateWithPut(@PathVariable(value = "eventId") int eventId, @RequestBody @Validated Event event) {
        LOGGER.info("PUT /events/{}", eventId);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "Delete an Event")
    public void delete(@PathVariable(value = "eventId") int eventId) {
        LOGGER.info("DELETE /events/{}", eventId);
        eventService.deleteEvent(eventId);
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }


}
