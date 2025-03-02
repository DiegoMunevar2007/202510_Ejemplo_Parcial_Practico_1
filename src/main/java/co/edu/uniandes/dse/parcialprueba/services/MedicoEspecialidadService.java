package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public void addEspecialidad (Long idMedico, Long idEspecialidad) throws EntityNotFoundException{
        log.info("Inició el proceso de adición de la especialidad al médico");
        Optional<MedicoEntity> medicoEncontrado = medicoRepository.findById(idMedico);
        if (!medicoEncontrado.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado al medico con ID "+idMedico);
        }
        Optional<EspecialidadEntity> especialidadEncontrada = especialidadRepository.findById(idEspecialidad);
        if (!especialidadEncontrada.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado a la especialidad con ID "+idEspecialidad);
        }
        medicoEncontrado.get().getEspecialidades().add(especialidadEncontrada.get());
        medicoRepository.save(medicoEncontrado.get());

    }
}
