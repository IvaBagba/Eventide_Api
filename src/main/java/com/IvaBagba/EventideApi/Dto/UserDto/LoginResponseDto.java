package com.IvaBagba.EventideApi.Dto.UserDto;

import com.IvaBagba.EventideApi.Models.CursosTags;
import com.IvaBagba.EventideApi.Models.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseDto {



    private Long  id;
    private String dni;
    private String userName;
    private String userSurname;
    private UserRoles userRole;
    private List<CursosTags> cursosTags;
}
