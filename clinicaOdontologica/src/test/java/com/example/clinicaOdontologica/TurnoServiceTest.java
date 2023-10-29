package com.example.clinicaOdontologica;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.example.clinicaOdontologica.service.TurnoService;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    static void doBefore() {
        paciente = new Paciente("Daniela", "Robles","Calle 123, Bogota, Colombia", 123654789, LocalDate.parse("2023-04-20"));
        odontologo = new Odontologo("ZZ4-146", "Alejandra", "Munoz");
    }

    @Test
    @Order(1)
    void registrarTurno() {
        Paciente miPaciente = pacienteService.registrarPaciente(paciente);
        Odontologo miOdontologo = odontologoService.registrarOdontologo(odontologo);
        Turno miTurno = new Turno(odontologo,paciente,LocalDate.parse("2023-11-21"));
        try {
            Assertions.assertEquals(1L, turnoService.registrarTurno(miTurno).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void mostrarTurnos() {
        List<Turno> listaTurnos = turnoService.mostrarTurnos();
        Assertions.assertTrue(listaTurnos.size() > 0);
    }

    @Test
    @Order(4)
    void modificarTurno() {
        Turno turnoModificado = new Turno(1L,odontologo,paciente,LocalDate.parse("2023-12-21"));

        try {
            Assertions.assertEquals(LocalDate.of(2023, 12, 21 ), turnoService.modificarTurno(turnoModificado).getFechaHora());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    void buscarTurno() {
        Assertions.assertNotNull(turnoService.buscarTurno(1L));

    }

    @Test
    @Order(5)
    void eliminarTurno() {
        try {
            turnoService.eliminarTurno(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

}
