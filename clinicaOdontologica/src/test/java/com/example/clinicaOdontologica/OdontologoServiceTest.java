package com.example.clinicaOdontologica;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registraOdontologo() {

        Odontologo odontologoEntradaDto = new Odontologo("ZZ4-146", "Alejandra", "Munoz");
        Odontologo rtaObtenida = odontologoService.registrarOdontologo(odontologoEntradaDto);
        assertEquals("ZZ4-146", rtaObtenida.getMatricula());
    }

    @Test
    @Order(2)
    void mostrarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoService.mostrarOdontologos();
        assertNotNull(listaOdontologos);
    }

    @Test
    @Order(3)
    void buscarOdontologo() {
        assertEquals("Alejandra", odontologoService.buscarOdontologo(1L).getNombre());
    }

    @Test
    @Order(4)
    void modificarOdontologo() throws ResourceNotFoundException {
        Odontologo odontologoModificar = new Odontologo(1L,"ZZ4-146", "Daniel", "Perez");

        assertEquals("Daniel", odontologoService.modificarOdontologo(odontologoModificar).getNombre());
    }

    @Test
    @Order(5)
    void elimianrOdontologo() {
        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}
