package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class EspecialidadEntity extends BaseEntity {

    private String nombre;
    private String descripcion;
}
