package com.eventplus.domain.event;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.dto.EventRegistrationData;
import com.eventplus.domain.organizer.Organizer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
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
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "endDate")
    private LocalDateTime endDate;
    private BigDecimal price;
    private Integer maxParticipants;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean active = true;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    public void cancel(String cancellation_reason){
        this.cancellationReason = cancellation_reason;
    }

    public void updateStatus() {
        var now = LocalDateTime.now();

        if (cancellationReason != null && !cancellationReason.isBlank()) {
            this.status = EventStatus.CANCELED;
        } else if (!now.isBefore(endDate)) {
            this.status = EventStatus.CONCLUDED;
        } else if (!now.isBefore(startDate)) {
            this.status = EventStatus.IN_PROGRESS;
        } else {
            this.status = EventStatus.SCHEDULED;
        }
    }

    public Event(EventRegistrationData data) {
        this.title = data.title();
        this.description = data.description();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.price = data.price();
        this.maxParticipants = data.maxParticipants();
        this.address = data.address();
    }
}
