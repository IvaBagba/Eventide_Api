package com.IvaBagba.EventideRework.Services;

import com.IvaBagba.EventideRework.Dto.EventideDataFieldsDto;
import com.IvaBagba.EventideRework.Dto.EventideResponseEventDto;
import com.IvaBagba.EventideRework.Models.CursosTags;
import com.IvaBagba.EventideRework.Models.EventStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventideDataService {
    public EventideDataFieldsDto getEventFields() {
        EventideDataFieldsDto eventideDataFieldsDto = new EventideDataFieldsDto();
        
        List<String> eventStatus = Arrays.stream(EventStatus.values()).map(Enum::name).toList();
        
        List<String> cursosTags = Arrays.stream(CursosTags.values()).map(Enum::name).toList();
        
        eventideDataFieldsDto.setEventStatus(eventStatus);
        eventideDataFieldsDto.setCursosTags(cursosTags);
        
        return  eventideDataFieldsDto;
    }
}
