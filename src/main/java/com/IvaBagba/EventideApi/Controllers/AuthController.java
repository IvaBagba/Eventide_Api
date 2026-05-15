package com.IvaBagba.EventideApi.Controllers;

import com.IvaBagba.EventideApi.Dto.UserDto.LoginRequestDto;
import com.IvaBagba.EventideApi.Dto.UserDto.LoginResponseDto;
import com.IvaBagba.EventideApi.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eventide/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequest
    ) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
