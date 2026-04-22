package com.IvaBagba.EventideRework.Controllers;


import com.IvaBagba.EventideRework.Dto.EventideCreateEventDto;
import com.IvaBagba.EventideRework.Dto.EventideResponseEventDto;
import com.IvaBagba.EventideRework.Models.EventideEvent;
import com.IvaBagba.EventideRework.Services.EventideEventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})

@RestController
@RequestMapping("/eventide/events")
public class EventideEventController  {

    private final EventideEventsService eventideEventsService;

    public EventideEventController(EventideEventsService eventideEventsService) {
        this.eventideEventsService = eventideEventsService;
    }
//Ver todos los eventos
    @GetMapping
    public ResponseEntity<List<EventideResponseEventDto>> getEventos(){
        return ResponseEntity.ok(eventideEventsService.getEventos());
    }
//Buscar evento por id
    @GetMapping("/{id}")
    public ResponseEntity<EventideResponseEventDto> getEventosById(@PathVariable long id){
        return ResponseEntity.ok(eventideEventsService.getEventoById(id));
    }

//Crear nuevo evento
    @PostMapping
    public ResponseEntity<EventideResponseEventDto> addEvento(@RequestBody EventideCreateEventDto eventDto) {
        return ResponseEntity.ok(eventideEventsService.addEvento(eventDto));
    }
//Modificar evento
    @PutMapping("/{id}")
    public ResponseEntity<EventideResponseEventDto> updateEvento(
            @PathVariable long id,
            @RequestBody EventideCreateEventDto eventDto
    ) {
        return ResponseEntity.ok(eventideEventsService.updateEvento(id, eventDto));
    }
//Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable long id) {
        eventideEventsService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
