package com.eventplus.domain.event;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.organizer.Organizer;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "event")
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private BigDecimal value;
    private Integer maxParticipants;
    private Boolean active;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public Event(@Valid EventRegistrationData data) {
        this.title = data.title();
        this.description = data.description();
        this.start_date = data.start_date();
        this.end_date = data.end_date();
        this.value = data.value();
        this.category = data.category();
    }
}
