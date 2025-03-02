package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@DataJpaTest
@Import({MedicoService.class})
class MedicoServiceTest {

    @Autowired
    private MedicoService medicoService;

    private PodamFactory factory;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        factory= new PodamFactoryImpl();
    }

    @Test
    void createMedicoTest()throws IllegalOperationException {
        MedicoEntity newEspecialidad = factory.manufacturePojo(MedicoEntity.class);
        newEspecialidad.setRegistroMedico("RM12534");
        MedicoEntity especialidadCreada = medicoService.createMedicos(newEspecialidad);
        assertNotNull(especialidadCreada);
        assertEquals(newEspecialidad, especialidadCreada);
        entityManager.persist(especialidadCreada);
    }
    @Test
    void createMedicoTestFail()  {
        MedicoEntity newEspecialidad = factory.manufacturePojo(MedicoEntity.class);
        newEspecialidad.setRegistroMedico("12534");
        assertThrows(IllegalOperationException.class ,()->{
            medicoService.createMedicos(newEspecialidad);
        });
         
    }
}
