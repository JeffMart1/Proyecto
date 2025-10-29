package com.example.demo.service;

import com.example.demo.entities.IncidenteEntity;
import com.example.demo.repository.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidenteServiceImpl {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Transactional
    public IncidenteEntity save(IncidenteEntity incidente) {
        return incidenteRepository.save(incidente);
    }

    @Transactional(readOnly = true)
    public List<IncidenteEntity> findAll() {
        return (List<IncidenteEntity>) incidenteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public IncidenteEntity findById(Integer id) {
        return incidenteRepository.findById(id).orElse(null);
    }
}