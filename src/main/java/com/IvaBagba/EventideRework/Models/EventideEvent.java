package com.IvaBagba.EventideRework.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Table(name = "events")
@Entity

@ToString(exclude = {"usuariosApuntados"})
@EqualsAndHashCode(exclude = {"usuariosApuntados"})

public class EventideEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String eventDesc;

    private LocalDate eventDate;
    private LocalTime eventTime;

    private String eventLocation;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CursosTags> cursosTags;

    @ManyToMany(mappedBy = "eventosApuntados")
    private List<EventideUser> usuariosApuntados;
}
