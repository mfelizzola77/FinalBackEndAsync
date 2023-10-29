package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @Override
    public Paciente registrarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
        LOGGER.info("Paciente registrado: ",paciente);
        return paciente;
    }
    
    @Override
    public List<Paciente> mostrarPacientes(){
        List<Paciente> lstPaciente = pacienteRepository.findAll();
        LOGGER.info("Lista de Pacientes: ",lstPaciente);
        return lstPaciente;
    }
    
    @Override
    public Paciente buscarPaciente(Long id){
        Paciente miPaciente = pacienteRepository.findById(id).orElse(null);
        if(miPaciente != null){
            LOGGER.info("Paciente encontrado: ",miPaciente);
        }else{
            LOGGER.info("Paciente no encontrado");
        }
        return miPaciente;
    }
    
    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Paciente miPaciente = pacienteRepository.findById(id).orElse(null);

        if(miPaciente != null){
            pacienteRepository.delete(miPaciente);
            LOGGER.info("Paciente a ser eliminado: ",miPaciente);
        }else{
            LOGGER.error("Paciente no encontrado");
            throw new ResourceNotFoundException("Paciente no encontrado con id: "+id);
        }
    }
    
    @Override
    public Paciente modificarPaciente(Paciente pacienteModificado) throws ResourceNotFoundException {
        Paciente miPaciente=null;
        if(buscarPaciente(pacienteModificado.getId()) != null){
            miPaciente = pacienteRepository.save(pacienteModificado);
            LOGGER.info("Paciente modificado exitosamente: ",pacienteModificado);

        }else{
            LOGGER.error("Paciente no encontrado");
            throw new ResourceNotFoundException("Paciente no encontrado con id: "+pacienteModificado.getId());
        }
        return miPaciente;
    }
}