package com.example.clinicaOdontologica.service;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.repository.TurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno registrarTurno(Turno miTurno){
        turnoRepository.save(miTurno);
        LOGGER.info("Turno registrado: ",miTurno);
        return miTurno;
    }

    @Override
    public List<Turno> mostrarTurnos(){
        List<Turno> lstTurno = turnoRepository.findAll();
        LOGGER.info("Lista de Turnos: ",lstTurno);
        return lstTurno;
    }

    @Override
    public Turno buscarTurno(Long id){
        Turno miTurno = turnoRepository.findById(id).orElse(null);
        if(miTurno != null){
            LOGGER.info("Turno encontrado: ",miTurno);
        }else{
            LOGGER.info("Turno no encontrado");
        }
        return miTurno;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Turno miTurno = turnoRepository.findById(id).orElse(null);

        if(miTurno != null){
            turnoRepository.delete(miTurno);
            LOGGER.info("Turno a ser eliminado: ",miTurno);
        }else{
            LOGGER.error("Turno no encontrado");
            throw new ResourceNotFoundException("Turno no encontrado con id: "+id);
        }
    }

    @Override
    public Turno modificarTurno(Turno turnoModificado) throws ResourceNotFoundException {
        Turno miTurno=null;
        if(buscarTurno(turnoModificado.getId()) != null){
            miTurno = turnoRepository.save(turnoModificado);
            LOGGER.info("Turno modificado exitosamente: ",turnoModificado);

        }else{
            LOGGER.error("Turno no encontrado");
            throw new ResourceNotFoundException("Turno no encontrado con id: "+turnoModificado.getId());
        }
        return miTurno;
    }
}