package com.api.rest.controller;

import com.api.rest.entities.Empleado;
import com.api.rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado guardarEmp(@RequestBody Empleado empleado){
        return empleadoService.guardarEmp(empleado);
    }

    @GetMapping
    public List<Empleado> listarEmp(){
        return empleadoService.getTodosEmp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable("id") Long empleadoId){
        return empleadoService.getEmpleadoPorId(empleadoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmp(@PathVariable("id") Long empleadoId,@RequestBody Empleado empleado){
        return empleadoService.getEmpleadoPorId(empleadoId)
                .map(empleadoGuardado ->{empleadoGuardado.setNombre(empleado.getNombre());
                    empleadoGuardado.setApellido(empleado.getApellido());
                    empleadoGuardado.setEmail(empleado.getEmail());

                    Empleado empleadoactualizado = empleadoService.actualizarEmp(empleadoGuardado);
                    return new ResponseEntity<>(empleadoactualizado,HttpStatus.OK);
    })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmp(@PathVariable("id") long empleadoId){
        empleadoService.borrarEmp(empleadoId);
        return new ResponseEntity<String>("Eliminado correctamente",HttpStatus.OK);
    }


    }