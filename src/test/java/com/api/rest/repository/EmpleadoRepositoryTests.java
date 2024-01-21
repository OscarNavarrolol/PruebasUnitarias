package com.api.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.api.rest.entities.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

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

    @DisplayName("test de Guardar resultado")
    @Test
    void testGuardarEmp(){
        // condicion a configurar o given
        Empleado empleado = Empleado.builder()
                .nombre("Carlo")
                .apellido("Embark")
                .email("carlosEmbark@gmail.com")
                .build();

        // when  comportamiento a configurar o comprobar

        Empleado empGuardado = empleadoRepository.save(empleado);


        //then verificar la salida
        assertThat(empGuardado).isNotNull();
        assertThat(empGuardado.getId()).isGreaterThan(0);
    }

    @DisplayName("Test lista empleado")
    @Test
    void testListarEmps(){
        Empleado empleado = Empleado.builder()
                .nombre("Astral")
                .apellido("Ziparis")
                .email("AstralZip@gmail.com")
                .build();

        empleadoRepository.save(empleado);
        empleadoRepository.save(empleado1);

        // when

        List<Empleado> listEmp = empleadoRepository.findAll();

        // then
        assertThat(listEmp).isNotNull();
        assertThat(listEmp.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Test para obtener empleado por Id")
    void testObtenerEmpById(){
        empleadoRepository.save(empleado1);

        //when
        Empleado empleadobd = empleadoRepository.findById((int) empleado1.getId()).get();
        //given
        assertThat(empleadobd).isNotNull();
    }
    @Test
    @DisplayName("Test Actualizar Empleado")
    void  testActualizarEmp(){
        empleadoRepository.save(empleado1);

        // when

        Empleado empleadoSave = empleadoRepository.findById((int)empleado1.getId()).get();
        empleadoSave.setEmail("empleadillo@gmail.com");
        empleadoSave.setNombre("empdillo");
        empleadoSave.setApellido("Illoreno");
        Empleado empleadoUpdate = empleadoRepository.save(empleadoSave);

        // given

        assertThat(empleadoUpdate.getEmail()).isEqualTo("empleadillo@gmail.com");
        assertThat(empleadoUpdate.getNombre()).isEqualTo("empdillo");
    }

}
