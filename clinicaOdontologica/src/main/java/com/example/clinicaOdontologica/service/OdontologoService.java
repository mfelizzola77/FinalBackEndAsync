package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
        LOGGER.info("Odontologo registrado: ",odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> mostrarOdontologos(){
        List<Odontologo> lstOdontologo = odontologoRepository.findAll();
        LOGGER.info("Lista de odontologos: ",lstOdontologo);
        return lstOdontologo;
    }

    @Override
    public Odontologo buscarOdontologo(Long id){
        Odontologo miOdontologo = odontologoRepository.findById(id).orElse(null);
        if(miOdontologo != null){
            LOGGER.info("Odontologo encontrado: ",miOdontologo);
        }else{
            LOGGER.info("Odontologo no encontrado");
        }
        return miOdontologo;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Odontologo miOdontologo = odontologoRepository.findById(id).orElse(null);

        if(miOdontologo != null){
            odontologoRepository.delete(miOdontologo);
            LOGGER.info("Odontologo a ser eliminado: ",miOdontologo);
        }else{
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("Odontologo no encontrado con id: "+id);
        }
    }

    @Override
    public Odontologo modificarOdontologo(Odontologo odontologoModificado) throws ResourceNotFoundException {
        Odontologo miOdontologo=null;
        if(buscarOdontologo(odontologoModificado.getId()) != null){
            miOdontologo = odontologoRepository.save(odontologoModificado);
            LOGGER.info("Odontologo modificado exitosamente: "+odontologoModificado);

        }else{
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("Odontologo no encontrado con id: "+odontologoModificado.getId());
        }
        return miOdontologo;
    }
}