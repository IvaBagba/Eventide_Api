package com.IvaBagba.EventideApi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//Creamos una configuracion para springboot
@Configuration
public class PassConfig {

    //Guardamos el objeto para usarlo mas adelante y no tener que crearlo individualmente en cada uso
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
