package com.IvaBagba.EventideApi.Services;

import com.IvaBagba.EventideApi.Dto.UserDto.LoginRequestDto;
import com.IvaBagba.EventideApi.Dto.UserDto.LoginResponseDto;
import com.IvaBagba.EventideApi.Models.EventideUser;
import com.IvaBagba.EventideApi.Repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto request) {
        EventideUser eventideUser = userRepository.findByDni(request.getDni())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passMatches = passwordEncoder.matches(
                request.getUserPass(),
                eventideUser.getUserPass()
        );

        if (passMatches) {
            return new LoginResponseDto(
                    eventideUser.getId(),
                    eventideUser.getDni(),
                    eventideUser.getUserName(),
                    eventideUser.getUserSurname(),
                    eventideUser.getUserRole(),
                    eventideUser.getCursosTags()
            );
        } else  {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}
