package com.IvaBagba.EventideApi.Repo;

import com.IvaBagba.EventideApi.Models.EventideUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EventideUser, Long> {

    //Metodo para crear querys personalizadas a las busquedas que necesitemos
    Optional<EventideUser> findByDni(String dni);

}
