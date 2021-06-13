package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String place;

    @Column(length = 100)
    private String speaker;

    @Column
    private LocalDateTime dateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public Event(String title, String place, String speaker, LocalDateTime dateTime, EventType eventType) {
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.eventType = eventType;
    }
}
