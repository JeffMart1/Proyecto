package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "listar_incidentes")
public class IncidenteEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidente")
    private Integer idIncidente;

    @NotNull(message = "El tipo de incidente es obligatorio")
    @Column(name = "tipo", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (tipo IN ('hardware', 'software', 'red', 'accesos'))")
    private String tipo;

    @NotNull(message = "La descripción es obligatoria")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    @Column(name = "prioridad", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (prioridad IN ('alta', 'media', 'baja'))")
    private String prioridad;

    @Column(name = "estado", nullable = true, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (estado IN ('abierto', 'en progreso', 'en espera', 'cerrado'))")
    private String estado;

    @Column(name = "tecnico", nullable = true, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (tecnico IN ('pedro', 'felipe', 'juan', 'peter', 'rafael'))")
    private String tecnico;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    // Relación One-to-Many con SeguimientoIncidenteEntity
    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeguimientoIncidenteEntity> seguimientos;

    // Relación One-to-Many con ConsultaEntity
    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultaEntity> consultas;

    // Constructores
    public IncidenteEntity() {
    }

    public IncidenteEntity(String tipo, String descripcion, String prioridad,
                           String estado, String tecnico) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tecnico = tecnico;
    }

    // Getters y Setters
    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<SeguimientoIncidenteEntity> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<SeguimientoIncidenteEntity> seguimientos) {
        this.seguimientos = seguimientos;
    }

    public List<ConsultaEntity> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaEntity> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return "IncidenteEntity{" +
                "idIncidente=" + idIncidente +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", estado='" + estado + '\'' +
                ", tecnico='" + tecnico + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}