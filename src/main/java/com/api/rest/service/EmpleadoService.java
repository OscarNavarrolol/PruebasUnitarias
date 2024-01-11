package com.api.rest.service;

import com.api.rest.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    Empleado guardarEmp(Empleado empleado);
    List<Empleado> getTodosEmp();
    Optional<Empleado> getEmpleadoPorId(long id);
    Empleado actualizarEmp(Empleado empleadoActualizado);
    void borrarEmp(long id);
}
