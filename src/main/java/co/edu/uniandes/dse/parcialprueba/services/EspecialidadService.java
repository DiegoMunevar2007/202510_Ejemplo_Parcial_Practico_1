package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws IllegalOperationException{
        log.info("Ha iniciado el proceso de creación de la especialidad con ID "+especialidadEntity.getId());
        if (especialidadEntity.getDescripcion().length()<10){
            throw new IllegalOperationException("La descripción debe de tener más de 10 caracteres");
        }
        log.info("Ha terminado el proceso de creación de la especialidad con ID "+especialidadEntity.getId());
        return especialidadRepository.save(especialidadEntity);
    }
}
