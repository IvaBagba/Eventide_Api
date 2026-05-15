package com.IvaBagba.EventideApi.Controllers;

import com.IvaBagba.EventideApi.Dto.DataDto.DataFieldsDto;
import com.IvaBagba.EventideApi.Services.DataService;
import com.IvaBagba.EventideApi.Services.EventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventide/data")
public class DataController {
    final EventsService eventsService;
    private final DataService dataService;

    public DataController(EventsService eventsService, DataService dataService) {
        this.eventsService = eventsService;
        this.dataService = dataService;
    }

    @GetMapping("/eventFields")
    public ResponseEntity<DataFieldsDto> getEventFields() {
        return ResponseEntity.ok(dataService.getEventFields());
    }
}
