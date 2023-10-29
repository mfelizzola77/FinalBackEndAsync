package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    Turno registrarTurno(Turno miTurno);

    List<Turno> mostrarTurnos();

    Turno buscarTurno(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    Turno modificarTurno(Turno turnoModificado) throws ResourceNotFoundException;
}
