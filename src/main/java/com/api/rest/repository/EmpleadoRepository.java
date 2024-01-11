package com.api.rest.repository;

import com.api.rest.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {

    Optional<Empleado> findByEmail(String email);
}
