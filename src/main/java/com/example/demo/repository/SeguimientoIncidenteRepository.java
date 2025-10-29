package com.example.demo.repository;

import com.example.demo.entities.SeguimientoIncidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoIncidenteRepository extends JpaRepository<SeguimientoIncidenteEntity, Integer> {
    List<SeguimientoIncidenteEntity> findByIncidenteIdIncidenteOrderByFechaModificacionDesc(Integer idIncidente);
}
