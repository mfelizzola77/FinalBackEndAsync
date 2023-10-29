package com.example.clinicaOdontologica.controllers;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.service.ITurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;
    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST - endpoint para registrar un turno
    @Operation(summary = "Registro de un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Turno.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("registrar")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno miTurno) {
        return new ResponseEntity<>(turnoService.registrarTurno(miTurno), HttpStatus.CREATED);
    }

    //PUT - Endpoint para modificar turno
    @Operation(summary = "Modificación de un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Turno.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("modificar")
    public ResponseEntity<Turno> modificarTurno(@RequestBody Turno miTurnoMod) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.modificarTurno(miTurnoMod), HttpStatus.OK);
    }

    //GET - Endpoint para buscar turno por id
    @Operation(summary = "Buscar turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Turno.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("buscar/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurno(id), HttpStatus.OK);
    }

    //DELET - Endpoint para eliminar turno por id
    @Operation(summary = "Eliminación de un turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }

    //Get - Endpoint para listar turnos
    @Operation(summary = "Listado de todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de turnos obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Turno.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listarTurnos")
    public ResponseEntity<List<Turno>> listarTurnos() {
        return new ResponseEntity<>(turnoService.mostrarTurnos(), HttpStatus.OK);
    }


}
