package com.IvaBagba.EventideApi.Repo;

import com.IvaBagba.EventideApi.Models.EventideEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<EventideEvent, Long> {
}
