package com.example.demo.entities;

import com.jdc.web.enums.TipoIncidente;
import com.jdc.web.enums.EstadoIncidente;
import com.jdc.web.enums.PrioridadIncidente;
import com.jdc.web.enums.TecnicoAsignado;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoIncidente tipo;

    @NotNull(message = "La descripción es obligatoria")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoIncidente estado;

    @NotNull(message = "La prioridad es obligatoria")
    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad", nullable = false, length = 20)
    private PrioridadIncidente prioridad;

    @NotNull(message = "El técnico asignado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tecnico", nullable = false, length = 20)
    private TecnicoAsignado tecnico;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    // Relación One-to-Many con SeguimientoIncidenteEntity
    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeguimientoIncidenteEntity> seguimientos;

    // Relación One-to-Many con TareaAsignadaEntity
    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TareaAsignadaEntity> tareas;

    // Constructores
    public IncidenteEntity() {
    }

    public IncidenteEntity(TipoIncidente tipo, String descripcion, EstadoIncidente estado,
                           PrioridadIncidente prioridad, TecnicoAsignado tecnico) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.tecnico = tecnico;
    }

    // Getters y Setters
    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public TipoIncidente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidente tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoIncidente getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidente estado) {
        this.estado = estado;
    }

    public PrioridadIncidente getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadIncidente prioridad) {
        this.prioridad = prioridad;
    }

    public TecnicoAsignado getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoAsignado tecnico) {
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

    public List<TareaAsignadaEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaAsignadaEntity> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "IncidenteEntity{" +
                "idIncidente=" + idIncidente +
                ", tipo=" + tipo +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", tecnico=" + tecnico +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}