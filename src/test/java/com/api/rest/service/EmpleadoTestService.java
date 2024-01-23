package com.api.rest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


import com.api.rest.entities.Empleado;
import com.api.rest.exception.ResourceNotFoundException;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.Impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmpleadoTestService {

    @Mock
    private EmpleadoRepository empleadoRepository;

    // inyectar el objeto con etiqueta en otro que lo necesita para funcionar
    @InjectMocks
    public EmpleadoServiceImpl empleadoService;

    private Empleado empleado1;

    @BeforeEach
    void setUp(){
        System.out.println("Compilado ");
        empleado1 = Empleado.builder()
                .nombre("Mauser")
                .apellido("Clinf")
                .email("Clinfs@gmail.com")
                .build();
    }

    @DisplayName("TestGuardarEmp")
    @Test
    void testGuardarEmp(){
        //given
        given(empleadoRepository.findByEmail(empleado1.getEmail()))
                .willReturn(Optional.empty());
        given(empleadoRepository.save(empleado1))
                .willReturn(empleado1);

        //when

        Empleado empleadoBBDD = empleadoService.guardarEmp(empleado1);

        //them

        assertThat(empleadoBBDD).isNotNull();
    }

    @DisplayName("TestGuardarEmp Con ThrowException")
    @Test
    void testGuardarEmpConThrowExcep(){
        //given
        given(empleadoRepository.findByEmail(empleado1.getEmail()))
                .willReturn(Optional.of(empleado1));

        //when
        assertThrows(ResourceNotFoundException.class,()-> { empleadoService.guardarEmp(empleado1);});

        //them

        verify(empleadoRepository,never()).save(any(Empleado.class));
    }

    @DisplayName("Test listar Empleados")
    @Test
    void testEliminarEmp(){

    }
}
