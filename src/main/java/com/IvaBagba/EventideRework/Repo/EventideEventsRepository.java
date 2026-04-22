package com.IvaBagba.EventideRework.Repo;

import com.IvaBagba.EventideRework.Models.EventideEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventideEventsRepository extends JpaRepository<EventideEvent, Long> {
}
