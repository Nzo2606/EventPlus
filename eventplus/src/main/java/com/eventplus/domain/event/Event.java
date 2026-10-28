package com.eventplus.domain.event;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.dto.registration.EventRegistrationData;
import com.eventplus.domain.event.dto.update.EventUpdateData;
import com.eventplus.domain.organizer.Organizer;
import com.eventplus.domain.registration.enums.PricingMode;
import com.eventplus.domain.registration.enums.RegistrationMode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "end_date")
    private LocalDateTime endDate;
    private BigDecimal price;
    private Integer maxParticipants;
    private Integer totalParticipants;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_mode", nullable = false)
    private RegistrationMode registrationMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_mode", nullable = false)
    private PricingMode pricingMode;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EventOccurrence> occurrences = new ArrayList<>();



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
        this.address = new Address(data.address());
    }

    public void updateData(EventUpdateData data) {
        if (data.title() != null){
            this.title = data.title();
        }
        if (data.description() != null){
            this.description = data.description();
        }
        if (data.startDate() != null){
            this.startDate = data.startDate();
        }
        if (data.endDate() != null){
            this.endDate = data.endDate();
        }
        if (data.price() != null){
            this.price = data.price();
        }
        if (data.maxParticipants() != null){
            this.maxParticipants = data.maxParticipants();
        }
        if (data.address() != null){
            this.address.updateData(data.address());
        }
    }


    public void addOccurrence(EventOccurrence occurrence){
        occurrences.add(occurrence);
        occurrence.setEvent(this);
    }

    public void removeOccurrence(EventOccurrence occurrence){
        occurrences.remove(occurrence);
        occurrence.setEvent(null);
    }
}
