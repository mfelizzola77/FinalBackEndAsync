package com.example.clinicaOdontologica;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void registrarPaciente() {
        Paciente miPaciente = new Paciente("Daniela", "Robles","Calle 123, Bogota, Colombia", 123654789, LocalDate.parse("2023-04-20"));
        Assertions.assertEquals("Daniela", pacienteService.registrarPaciente(miPaciente).getNombre());
    }

    @Test
    @Order(2)
    void mostrarPacientes() {
        List<Paciente> listaPacientes = pacienteService.mostrarPacientes();
        Assertions.assertNotNull(listaPacientes);
    }

    @Test
    @Order(4)
    void modificarPaciente() throws ResourceNotFoundException {

        Paciente pacienteModificado = new Paciente(1L, "Carlos", "Perea", "Calle 456, Bogota, Colombia",89485948, LocalDate.parse("2023-04-10"));

        Assertions.assertEquals("Carlos", pacienteService.modificarPaciente(pacienteModificado).getNombre());
    }

    @Test
    @Order(3)
    void buscarPaciente() {
        Assertions.assertNotNull(pacienteService.buscarPaciente(1L));
    }

    @Test
    @Order(5)
    void eliminarPaciente() {
        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }
}
