package com.IvaBagba.EventideApi.Controllers;


import com.IvaBagba.EventideApi.Dto.EventDto.CreateEventDto;
import com.IvaBagba.EventideApi.Dto.EventDto.ResponseEventDto;
import com.IvaBagba.EventideApi.Repo.EventsRepository;
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
//Buscar evento por los tags de usuario
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<ResponseEventDto>> getEventosByUser(@PathVariable long userID){
        return ResponseEntity.ok(eventsService.getEventosPerUserTag(userID));
    }

//Crear nuevo evento
    @PostMapping("/admin/{userID}")
    public ResponseEntity<ResponseEventDto> addEvento(@RequestBody CreateEventDto eventDto, @PathVariable long userID) {
        return ResponseEntity.ok(eventsService.addEvento(eventDto, userID));
    }
//Añadir usuario a la lista de un evento
    @PostMapping("/{id}/register/{userID}")
    public ResponseEntity<ResponseEventDto> registerToEvento(@PathVariable long id, @PathVariable long userID){
        return ResponseEntity.ok(eventsService.addUserToEvent(id,userID));
    }

//Modificar evento
    @PutMapping("/admin/{id}/{userID}")
    public ResponseEntity<ResponseEventDto> updateEvento(
            @PathVariable long id,
            @PathVariable long userID,
            @RequestBody CreateEventDto eventDto
    ) {
        return ResponseEntity.ok(eventsService.updateEvento(id, eventDto, userID));
    }
//Eliminar evento
    @DeleteMapping("/admin/{id}/{userID}")
    public ResponseEntity<Void> deleteEvento(@PathVariable long id,  @PathVariable long userID) {
        eventsService.deleteEvento(id, userID);
        return ResponseEntity.noContent().build();
    }
//Eliminar a un usuario de un evento
    @DeleteMapping("/{id}/register/{userID}")
    public ResponseEntity<ResponseEventDto> unregisterFromEvento(@PathVariable long id, @PathVariable long userID) {
        return ResponseEntity.ok(eventsService.removeUserFromEvent(id,userID));
    }


}
