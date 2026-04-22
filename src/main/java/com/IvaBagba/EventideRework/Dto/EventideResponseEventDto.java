package com.IvaBagba.EventideRework.Dto;

import com.IvaBagba.EventideRework.Models.CursosTags;
import com.IvaBagba.EventideRework.Models.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class EventideResponseEventDto {
    private long id;
    private String eventName;
    private String eventDesc;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String eventLocation;
    private EventStatus eventStatus;
    private List<CursosTags> cursosTags;
}
