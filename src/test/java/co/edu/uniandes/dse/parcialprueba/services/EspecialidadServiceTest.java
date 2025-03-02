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

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Import({EspecialidadService.class})
class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;

    private PodamFactory factory;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        factory= new PodamFactoryImpl();
    }

    @Test
    void createEspecialidadTest() throws IllegalOperationException{
        EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
        newEspecialidad.setDescripcion("Una increible descripción");
        EspecialidadEntity especialidadCreada = especialidadService.createEspecialidad(newEspecialidad);
        assertNotNull(especialidadCreada);
        assertEquals(newEspecialidad, especialidadCreada);
        entityManager.persist(especialidadCreada);

    }
    @Test
    void createEspecialidadTestFail(){
        EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
        newEspecialidad.setDescripcion("");
        assertThrows(IllegalOperationException.class, () -> {
            especialidadService.createEspecialidad(newEspecialidad);
        });

    }

}
