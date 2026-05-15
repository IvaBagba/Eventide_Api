package com.IvaBagba.EventideApi.Services;

import com.IvaBagba.EventideApi.Dto.EventDto.CreateEventDto;
import com.IvaBagba.EventideApi.Dto.EventDto.ResponseEventDto;
import com.IvaBagba.EventideApi.Models.EventideEvent;
import com.IvaBagba.EventideApi.Repo.EventsRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<ResponseEventDto> getEventos() {

        List<EventideEvent> eventosRaw = eventsRepository.findAll();
        List<ResponseEventDto> eventosDto = new ArrayList<>();

        for (EventideEvent eventideEvent : eventosRaw) {
            ResponseEventDto ResponseEventDto = new ResponseEventDto();
            ResponseEventDto.setId(eventideEvent.getId());
            ResponseEventDto.setEventName(eventideEvent.getEventName());
            ResponseEventDto.setEventDesc(eventideEvent.getEventDesc());
            ResponseEventDto.setEventDate(eventideEvent.getEventDate());
            ResponseEventDto.setEventLocation(eventideEvent.getEventLocation());
            ResponseEventDto.setEventTime(eventideEvent.getEventTime());
            ResponseEventDto.setCursosTags(eventideEvent.getCursosTags());
            ResponseEventDto.setEventStatus(eventideEvent.getEventStatus());

            eventosDto.add(ResponseEventDto);

        }
        return eventosDto;

    }

    public ResponseEventDto addEvento(CreateEventDto eventDto) {
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

    public ResponseEventDto updateEvento(long id, CreateEventDto req) {
        EventideEvent updEvent = eventsRepository.findById(id).get();

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

    public void deleteEvento(long id) {
            EventideEvent event = eventsRepository.findById(id).get();
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
        return eventideEvent;
    }
}
