package com.api.rest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
                .id(1L)
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

    @Test
    @DisplayName("Test para listar")
    void testLimpiarEmp(){
        Empleado empleado = Empleado.builder()
                .id(1l)
                .nombre("pepe")
                .apellido("roscar")
                .email("roca@gmail.com")
                .build();


        given(empleadoRepository.findAll()).willReturn(List.of(empleado1,empleado));

        // when

        List<Empleado> empleados =empleadoService.getTodosEmp();

        // them

        assertThat(empleados).isNotNull();
        assertThat(empleados.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("Test lista conexion vacia")
    void testListarColeccionEmpVacia(){
        Empleado empleado = Empleado.builder()
                .id(1l)
                .nombre("pepe")
                .apellido("roscar")
                .email("roca@gmail.com")
                .build();


        given(empleadoRepository.findAll()).willReturn(Collections.emptyList());

        // when

        List<Empleado> empleadosClean = empleadoService.getTodosEmp();

        //
        assertThat(empleadosClean).isEmpty();
        assertThat(empleadosClean.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Test para obtener empleado por ID")
    void  testObtenerEmpById(){

        given(empleadoRepository.findById((int)1L)).willReturn(Optional.of(empleado1));

        // when
        Empleado emp = empleadoService.getEmpleadoPorId(empleado1.getId()).get();

       // then
        assertThat(emp.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Actualizar empleado")
    void testActualizarEmp(){

        given(empleadoRepository.save(empleado1)).willReturn(empleado1);
        empleado1.setNombre("Carlos");
        empleado1.setApellido("Garrera");
        empleado1.setEmail("Carleto@gmail.com");

        // when
        Empleado empleadoActualziado = empleadoService.actualizarEmp(empleado1);

        // then

        assertThat(empleadoActualziado.getNombre()).isEqualTo("Carlos");
        assertThat(empleadoActualziado.getApellido()).isEqualTo("Garrera");
    }

    @Test
    @DisplayName("Elimar empleado")
    void testBorarEmp(){
        long empleadoId = 1L;
        willDoNothing().given(empleadoRepository).deleteById((int) empleadoId);

        empleadoService.borrarEmp(empleadoId);

        verify(empleadoRepository,times(1)).deleteById((int)empleadoId);
    }

}
