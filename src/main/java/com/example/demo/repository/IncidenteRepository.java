package com.example.demo.repository;

import com.example.demo.entities.IncidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteRepository extends CrudRepository<IncidenteEntity, Integer> {

    // Buscar incidentes sin técnico asignado (campo vacío o null)
    List<IncidenteEntity> findByTecnicoIsNullOrTecnico(String tecnico);

    // Buscar por estado
    List<IncidenteEntity> findByEstado(String estado);

    // Buscar por prioridad
    List<IncidenteEntity> findByPrioridad(String prioridad);

    // Buscar por tipo
    List<IncidenteEntity> findByTipo(String tipo);

    // Ordenar por fecha de creación descendente
    List<IncidenteEntity> findAllByOrderByFechaCreacionDesc();
}