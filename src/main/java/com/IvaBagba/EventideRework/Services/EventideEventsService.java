package com.IvaBagba.EventideRework.Services;

import com.IvaBagba.EventideRework.Dto.EventideCreateEventDto;
import com.IvaBagba.EventideRework.Dto.EventideResponseEventDto;
import com.IvaBagba.EventideRework.Models.EventideEvent;
import com.IvaBagba.EventideRework.Repo.EventideEventsRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventideEventsService {

    private final EventideEventsRepository eventideEventsRepository;

    public EventideEventsService(EventideEventsRepository eventideEventsRepository) {
        this.eventideEventsRepository = eventideEventsRepository;
    }

    public List<EventideResponseEventDto> getEventos() {

        List<EventideEvent> eventosRaw = eventideEventsRepository.findAll();
        List<EventideResponseEventDto> eventosDto = new ArrayList<>();

        for (EventideEvent eventideEvent : eventosRaw) {
            EventideResponseEventDto EventideResponseEventDto = new EventideResponseEventDto();
            EventideResponseEventDto.setId(eventideEvent.getId());
            EventideResponseEventDto.setEventName(eventideEvent.getEventName());
            EventideResponseEventDto.setEventDesc(eventideEvent.getEventDesc());
            EventideResponseEventDto.setEventDate(eventideEvent.getEventDate());
            EventideResponseEventDto.setEventLocation(eventideEvent.getEventLocation());
            EventideResponseEventDto.setEventTime(eventideEvent.getEventTime());
            EventideResponseEventDto.setCursosTags(eventideEvent.getCursosTags());
            EventideResponseEventDto.setEventStatus(eventideEvent.getEventStatus());

            eventosDto.add(EventideResponseEventDto);

        }
        return eventosDto;

    }

    public EventideResponseEventDto addEvento(EventideCreateEventDto eventDto) {
        EventideEvent eventideEvent = new EventideEvent();
        eventideEvent.setEventName(eventDto.getEventName());
        eventideEvent.setEventDesc(eventDto.getEventDesc());
        eventideEvent.setEventDate(eventDto.getEventDate());
        eventideEvent.setEventLocation(eventDto.getEventLocation());
        eventideEvent.setEventTime(eventDto.getEventTime());
        eventideEvent.setCursosTags(eventDto.getCursosTags());
        eventideEvent.setEventStatus(eventDto.getEventStatus());

        eventideEventsRepository.save(eventideEvent);
        return toResponseEntity(eventideEvent);
    }

    public EventideResponseEventDto updateEvento(long id, EventideCreateEventDto req) {
        EventideEvent updEvent = eventideEventsRepository.findById(id).get();

        updEvent.setEventName(req.getEventName());
        updEvent.setEventDesc(req.getEventDesc());
        updEvent.setEventDate(req.getEventDate());
        updEvent.setEventLocation(req.getEventLocation());
        updEvent.setEventTime(req.getEventTime());
        updEvent.setCursosTags(req.getCursosTags());
        updEvent.setEventStatus(req.getEventStatus());

        eventideEventsRepository.save(updEvent);

        return toResponseEntity(updEvent);
    }

    public void deleteEvento(long id) {
            EventideEvent event = eventideEventsRepository.findById(id).get();
            eventideEventsRepository.delete(event);
    }

    public @Nullable EventideResponseEventDto getEventoById(long id) {
        EventideEvent eventById = eventideEventsRepository.findById(id).get();
        return toResponseEntity(eventById);
    }

    //Transformar entidad de la BBDD a DTO para respuesta
    private EventideResponseEventDto toResponseEntity(EventideEvent event) {
        EventideResponseEventDto eventideEvent = new EventideResponseEventDto();
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
