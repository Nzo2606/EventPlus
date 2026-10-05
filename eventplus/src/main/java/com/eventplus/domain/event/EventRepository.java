package com.eventplus.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

        Page<Event> findAllByActiveTrue(Pageable pagination);

        @Query("""
            select e
            from Event e
            where e.active = true
            and e.category.id = :id
        """)
        Page<Event> findActiveEventsByCategoryId(Long id, Pageable pagination);


        @Query("""
            select e
            from Event e
            where e.active = true
            and e.organizer.id = :id
        """)
        Page<Event> findActiveEventsByOrganizerId(Long id, Pageable pagination);

        Page<Event> findByStartDate(LocalDateTime startDate, Pageable pagination);
}
