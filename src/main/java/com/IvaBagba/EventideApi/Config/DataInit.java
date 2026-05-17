package com.IvaBagba.EventideApi.Config;

import com.IvaBagba.EventideApi.Models.CursosTags;
import com.IvaBagba.EventideApi.Models.EventideUser;
import com.IvaBagba.EventideApi.Models.UserRoles;
import com.IvaBagba.EventideApi.Repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements CommandLineRunner {


        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public DataInit(
                UserRepository userRepository,
                PasswordEncoder passwordEncoder
        ) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(String... args) {

            if (userRepository.findByDni("12345678A").isEmpty()) {

                EventideUser admin = new EventideUser();

                admin.setDni("12345678A");
                admin.setUserName("Admin");
                admin.setUserSurname("Eventide");

                // La contraseña real será: 1234
                // En base de datos se guarda hasheada.
                admin.setUserPass(passwordEncoder.encode("1234"));

                admin.setUserRole(UserRoles.ADMIN);
                admin.setCursosTags(List.of(
                        CursosTags.FP,
                        CursosTags.BACHILLER
                ));

                userRepository.save(admin);
            }

            if (userRepository.findByDni("87654321B").isEmpty()) {

                EventideUser user = new EventideUser();

                user.setDni("87654321B");
                user.setUserName("Usuario");
                user.setUserSurname("Prueba");

                // Contraseña real: 1234
                // En base de datos se guarda hasheada.
                user.setUserPass(passwordEncoder.encode("1234"));

                user.setUserRole(UserRoles.USER);

                user.setCursosTags(List.of(
                        CursosTags.FP
                ));

                userRepository.save(user);
            }
        }
}
