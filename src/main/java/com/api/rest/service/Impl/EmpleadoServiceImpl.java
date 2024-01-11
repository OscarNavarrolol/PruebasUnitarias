package com.api.rest.service.Impl;

import com.api.rest.entities.Empleado;
import com.api.rest.exception.ResourceNotFoundException;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado guardarEmp(Empleado empleado) {
        Optional<Empleado> empleadoGuardar = empleadoRepository.findByEmail(empleado.getEmail());
        if (empleadoGuardar.isPresent()){
            throw new ResourceNotFoundException("Existe un mail asi con otro empleado"+ empleado.getEmail());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> getTodosEmp() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoPorId(long id) {
        return empleadoRepository.findById((int) id);
    }

    @Override
    public Empleado actualizarEmp(Empleado empleadoActualizado) {
        return empleadoRepository.save(empleadoActualizado);
    }

    @Override
    public void borrarEmp(long id) {
        empleadoRepository.deleteById((int) id);
    }
}
