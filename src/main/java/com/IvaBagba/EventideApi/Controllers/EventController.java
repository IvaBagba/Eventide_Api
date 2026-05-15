package com.IvaBagba.EventideApi.Controllers;


import com.IvaBagba.EventideApi.Dto.EventDto.CreateEventDto;
import com.IvaBagba.EventideApi.Dto.EventDto.ResponseEventDto;
import com.IvaBagba.EventideApi.Services.EventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})

@RestController
@RequestMapping("/eventide/events")
public class EventController {

    private final EventsService eventsService;

    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }
//Ver todos los eventos
    @GetMapping
    public ResponseEntity<List<ResponseEventDto>> getEventos(){
        return ResponseEntity.ok(eventsService.getEventos());
    }
//Buscar evento por id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventDto> getEventosById(@PathVariable long id){
        return ResponseEntity.ok(eventsService.getEventoById(id));
    }

//Crear nuevo evento
    @PostMapping
    public ResponseEntity<ResponseEventDto> addEvento(@RequestBody CreateEventDto eventDto) {
        return ResponseEntity.ok(eventsService.addEvento(eventDto));
    }
//Modificar evento
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDto> updateEvento(
            @PathVariable long id,
            @RequestBody CreateEventDto eventDto
    ) {
        return ResponseEntity.ok(eventsService.updateEvento(id, eventDto));
    }
//Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable long id) {
        eventsService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
