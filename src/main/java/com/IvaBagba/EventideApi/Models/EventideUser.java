package com.IvaBagba.EventideApi.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = {"eventosApuntados"})
@EqualsAndHashCode(exclude = {"eventosApuntados"})
public class EventideUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles userRole;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String userName;

    private String userSurname;

    // Contraseña (almacenada como HASH, no texto plano)
    @Column(nullable = false)
    private String userPass;

    // Tags del usuario (colección de enums)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CursosTags> cursosTags;

    // Relación muchos a muchos con Evento
    @ManyToMany
    @JoinTable(
            name = "usuario_evento",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private List<EventideEvent> eventosApuntados = new ArrayList<>();
}
