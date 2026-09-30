package com.eventplus.domain.category;

import com.eventplus.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
