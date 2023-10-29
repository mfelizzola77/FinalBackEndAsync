package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> mostrarOdontologos();

    Odontologo buscarOdontologo(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    Odontologo modificarOdontologo(Odontologo odontologoModificado) throws ResourceNotFoundException;
}
