package repository;

import model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findByTitle(@Param("title")String title);

}
