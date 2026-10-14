package com.eventplus.domain.event;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.dto.EventRegistrationData;
import com.eventplus.domain.organizer.Organizer;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private BigDecimal price;
    private Integer maxParticipants;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean active;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private EventStatus status;

    private String cancellation_reason;

    public void cancel(String cancellation_reason){
        this.cancellation_reason = cancellation_reason;
    }

    public void setStatus() {
        if (cancellation_reason != null){
            this.status = EventStatus.CANCELED;
        }
        else if (end_date.isAfter(LocalDateTime.now())) {
            this.status = EventStatus.CONCLUDED;
        }
        else if (start_date.isBefore(LocalDateTime.now()) && end_date.isAfter(LocalDateTime.now())) {
            this.status = EventStatus.IN_PROGRESS;
        }
        else{
            this.status = EventStatus.SCHEDULED;
        }
    }

    public Event(@Valid EventRegistrationData data) {
        this.title = data.title();
        this.description = data.description();
        this.start_date = data.start_date();
        this.end_date = data.end_date();
        this.price = data.price();
        this.maxParticipants = data.maxParticipants();
        this.address = data.address();
        this.organizer = data.organizer();
        this.category = data.category();
    }
}
