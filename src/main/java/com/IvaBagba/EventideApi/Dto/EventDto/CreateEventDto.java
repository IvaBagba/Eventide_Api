package com.IvaBagba.EventideApi.Dto.EventDto;

import com.IvaBagba.EventideApi.Models.CursosTags;
import com.IvaBagba.EventideApi.Models.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateEventDto {

    private String eventName;
    private String eventDesc;

    private LocalDate eventDate;
    private LocalTime eventTime;

    private String eventLocation;

    private EventStatus eventStatus;

    private List<CursosTags> cursosTags;
}
