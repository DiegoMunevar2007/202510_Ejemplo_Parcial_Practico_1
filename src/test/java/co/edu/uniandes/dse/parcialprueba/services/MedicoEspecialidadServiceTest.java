package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Import({MedicoEspecialidadService.class})
class MedicoEspecialidadServiceTest {

    @Autowired
    private MedicoEspecialidadService service;


    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory;

    @BeforeEach
    void setUp(){
        factory=new PodamFactoryImpl();
    }

    @Test
    void addEspecialidad(){
        MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medicoEntity);
        EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(especialidadEntity);
        assertDoesNotThrow( () -> {service.addEspecialidad(medicoEntity.getId(), especialidadEntity.getId());});
        assertTrue(medicoEntity.getEspecialidades().contains(especialidadEntity));
    }

    @Test
    void addEspecialidadMedicoFail(){
        MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medicoEntity);
        EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(especialidadEntity);
        assertThrows(EntityNotFoundException.class, ()-> {
            service.addEspecialidad(371376712L, especialidadEntity.getId());
        });
    }

    @Test
    void addEspecialidadFail(){
        MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medicoEntity);
        EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(especialidadEntity);
        assertThrows(EntityNotFoundException.class, ()-> {
            service.addEspecialidad(medicoEntity.getId(), 345678126L);
        });
    }
}

