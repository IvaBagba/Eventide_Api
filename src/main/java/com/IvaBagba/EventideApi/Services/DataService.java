package com.IvaBagba.EventideApi.Services;

import com.IvaBagba.EventideApi.Dto.DataDto.DataFieldsDto;
import com.IvaBagba.EventideApi.Models.CursosTags;
import com.IvaBagba.EventideApi.Models.EventStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataService {
    public DataFieldsDto getEventFields() {
        DataFieldsDto dataFieldsDto = new DataFieldsDto();
        
        List<String> eventStatus = Arrays.stream(EventStatus.values()).map(Enum::name).toList();
        
        List<String> cursosTags = Arrays.stream(CursosTags.values()).map(Enum::name).toList();
        
        dataFieldsDto.setEventStatus(eventStatus);
        dataFieldsDto.setCursosTags(cursosTags);
        
        return dataFieldsDto;
    }
}
