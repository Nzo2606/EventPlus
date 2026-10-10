package com.eventplus.domain.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

//        Page<Event> findAllByActiveTrue(Pageable pagination);
//
//        @Query("""
//            select e
//            from Event e
//            where e.active = true
//            and e.category.id = :id
//        """)
//        Page<Event> findActiveEventsByCategoryId(Long id, Pageable pagination);
//
//
//        @Query("""
//            select e
//            from Event e
//            where e.active = true
//            and e.organizer.id = :id
//        """)
//        Page<Event> findActiveEventsByOrganizerId(Long id, Pageable pagination);
//
//        Page<Event> findByStartDate(LocalDateTime start_date, Pageable pagination);
}
