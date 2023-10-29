package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    Paciente registrarPaciente(Paciente paciente);

    List<Paciente> mostrarPacientes();

    Paciente buscarPaciente(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    Paciente modificarPaciente(Paciente pacienteModificado) throws ResourceNotFoundException;
}
