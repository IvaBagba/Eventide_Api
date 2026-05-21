package com.IvaBagba.EventideApi.Services;

import com.IvaBagba.EventideApi.Dto.EventDto.CreateEventDto;
import com.IvaBagba.EventideApi.Dto.EventDto.ResponseEventDto;
import com.IvaBagba.EventideApi.Models.*;
import com.IvaBagba.EventideApi.Repo.EventsRepository;
import com.IvaBagba.EventideApi.Repo.UserRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;
    private final UserRepository userRepository;

    public EventsService(EventsRepository eventsRepository, UserRepository userRepository) {
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
    }

    public List<ResponseEventDto> getEventos() {

        List<EventideEvent> eventosRaw = eventsRepository.findAll();
        List<ResponseEventDto> eventosDto = new ArrayList<>();

        for (EventideEvent eventideEvent : eventosRaw) {
            eventosDto.add(toResponseEntity(eventideEvent));
        }
        return eventosDto;

    }

    public ResponseEventDto addEvento(CreateEventDto eventDto, long userID) {
        isAdmin(userID);

        EventideEvent eventideEvent = new EventideEvent();
        eventideEvent.setEventName(eventDto.getEventName());
        eventideEvent.setEventDesc(eventDto.getEventDesc());
        eventideEvent.setEventDate(eventDto.getEventDate());
        eventideEvent.setEventLocation(eventDto.getEventLocation());
        eventideEvent.setEventTime(eventDto.getEventTime());
        eventideEvent.setCursosTags(eventDto.getCursosTags());
        eventideEvent.setEventStatus(eventDto.getEventStatus());

        eventsRepository.save(eventideEvent);
        return toResponseEntity(eventideEvent);
    }

    public ResponseEventDto updateEvento(long id, CreateEventDto req, long userID) {
        isAdmin(userID);

        EventideEvent updEvent = eventsRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        updEvent.setEventName(req.getEventName());
        updEvent.setEventDesc(req.getEventDesc());
        updEvent.setEventDate(req.getEventDate());
        updEvent.setEventLocation(req.getEventLocation());
        updEvent.setEventTime(req.getEventTime());
        updEvent.setCursosTags(req.getCursosTags());
        updEvent.setEventStatus(req.getEventStatus());

        eventsRepository.save(updEvent);

        return toResponseEntity(updEvent);
    }

    @Transactional
    public void deleteEvento(long id, long userID) {
            isAdmin(userID);

            EventideEvent event = eventsRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            for (EventideUser user : event.getUsuariosApuntados()){
                user.getEventosApuntados().remove(event);
            }

            event.getUsuariosApuntados().clear();
            eventsRepository.delete(event);
    }

    public @Nullable ResponseEventDto getEventoById(long id) {
        EventideEvent eventById = eventsRepository.findById(id).get();
        return toResponseEntity(eventById);
    }

    //Transformar entidad de la BBDD a DTO para respuesta
    private ResponseEventDto toResponseEntity(EventideEvent event) {
        ResponseEventDto eventideEvent = new ResponseEventDto();
        eventideEvent.setId(event.getId());
        eventideEvent.setEventName(event.getEventName());
        eventideEvent.setEventDesc(event.getEventDesc());
        eventideEvent.setEventDate(event.getEventDate());
        eventideEvent.setEventLocation(event.getEventLocation());
        eventideEvent.setEventTime(event.getEventTime());
        eventideEvent.setCursosTags(event.getCursosTags());
        eventideEvent.setEventStatus(event.getEventStatus());

        List<Long> regUsersID = event.getUsuariosApuntados().stream().map(EventideUser::getId).toList();

        eventideEvent.setRegUsersID(regUsersID);
        return eventideEvent;
    }

    //Filtrar eventos enviados al usuario segun sus tags y status del evento
    public List<ResponseEventDto> getEventosPerUserTag(Long userId) {
        EventideUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<EventideEvent> allEvents = eventsRepository.findAll();
        List<ResponseEventDto> filteredEvents = new ArrayList<>();

        for  (EventideEvent event : allEvents) {
            if(userEventCheck(user, event)){
                filteredEvents.add(toResponseEntity(event));
            }
        }

        return filteredEvents;
    }

    //Condicionales para que un evento se muestre
    private boolean userEventCheck(EventideUser user, EventideEvent event) {
        //Si es admin enviamos el evento
        if (user.getUserRole() == UserRoles.ADMIN) {
            return true;
        }

        //Si es un borrador o esta cancelado el evento no se puede ver
        if (event.getEventStatus() == EventStatus.BORRADOR || event.getEventStatus() == EventStatus.CANCELADO) {
            return false;
        }

        //Si el evento no tiene tags no se puede ver
        if (event.getCursosTags() == null || event.getCursosTags().isEmpty()) {
            return false;
        }

        //Si el evento tiene tags no es borrador ni cancelado y la tag que tiene es TODOS el evento se ve
        if (event.getCursosTags().contains(CursosTags.TODOS)) {
            return true;
        }

        //Si el USUARIO no tiene tags no ve los eventos que no sean para todos
        if (user.getCursosTags() == null || user.getCursosTags().isEmpty()) {
            return false;
        }

        //Si no como condicion final miramos que tags tiene el evento y vemos si usuario contiene al menos una de esas tags entonces se muestra el evento o no
        return event.getCursosTags().stream().anyMatch(user.getCursosTags()::contains);
    }

    //Transactional hace que solo se haga la accion si todos los cambios se hacen correctamente
    @Transactional
    public ResponseEventDto addUserToEvent(long id, long userID) {
        EventideEvent event = eventsRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        EventideUser user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //Condiciones en las que no puedes inscribirte a un evento
        //Si eres admin
        if (user.getUserRole() == UserRoles.ADMIN) {
            throw new RuntimeException("Los administradores no pueden inscribirse a los eventos");
        }
        //Si no puedes visualizar el evento (no esta en tu lista)
        if (!userEventCheck(user, event)) {
            throw new RuntimeException("No puedes apuntarte a este evento");
        }
        //Si la lista no esta abierta
        if (event.getEventStatus() != EventStatus.LISTA_ABIERTA) {
            throw new RuntimeException("La inscripción a este evento ha finalizado");
        }
        //---//

        if (event.getUsuariosApuntados().contains(user)) {
            return toResponseEntity(event);
        }

        event.getUsuariosApuntados().add(user);
        user.getEventosApuntados().add(event);

        eventsRepository.save(event);

        return toResponseEntity(event);
    }

    public ResponseEventDto removeUserFromEvent(long id, long userID) {
        EventideEvent event = eventsRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        EventideUser user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        event.getUsuariosApuntados().remove(user);
        user.getEventosApuntados().remove(event);
        eventsRepository.save(event);
        return toResponseEntity(event);
    }

    private EventideUser isAdmin(long userID) {
        EventideUser user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getUserRole() != UserRoles.ADMIN) {
            throw new RuntimeException("No tienes permisos");
        }

        return user;
    }
}
