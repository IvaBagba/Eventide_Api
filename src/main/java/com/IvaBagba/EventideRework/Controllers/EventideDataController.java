package com.IvaBagba.EventideRework.Controllers;

import com.IvaBagba.EventideRework.Dto.EventideDataFieldsDto;
import com.IvaBagba.EventideRework.Services.EventideDataService;
import com.IvaBagba.EventideRework.Services.EventideEventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventide/data")
public class EventideDataController {
    final EventideEventsService eventideEventsService;
    private final EventideDataService eventideDataService;

    public EventideDataController(EventideEventsService eventideEventsService, EventideDataService eventideDataService) {
        this.eventideEventsService = eventideEventsService;
        this.eventideDataService = eventideDataService;
    }

    @GetMapping("/eventFields")
    public ResponseEntity<EventideDataFieldsDto> getEventFields() {
        return ResponseEntity.ok(eventideDataService.getEventFields());
    }
}
