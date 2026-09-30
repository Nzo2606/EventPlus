package com.eventplus.domain.event;

import com.eventplus.domain.category.Category;
import com.eventplus.domain.organizer.Organizer;
import jakarta.persistence.*;
import lombok.*;

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
    private Double value;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
