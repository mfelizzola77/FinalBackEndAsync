package com.example.clinicaOdontologica.repository;
import com.example.clinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
