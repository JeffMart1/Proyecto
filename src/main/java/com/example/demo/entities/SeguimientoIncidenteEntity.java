package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento_incidentes")
public class SeguimientoIncidenteEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidente", nullable = false, foreignKey = @ForeignKey(name = "fk_seguimiento_incidente"))
    private IncidenteEntity incidente;

    @Column(name = "estado_anterior", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (estado_anterior IN ('abierto', 'en progreso', 'en espera', 'cerrado'))")
    private String estadoAnterior;

    @Column(name = "estado_actual", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (estado_actual IN ('abierto', 'en progreso', 'en espera', 'cerrado'))")
    private String estadoActual;

    @Size(max = 255, message = "Los comentarios no pueden exceder 255 caracteres")
    @Column(name = "comentarios", length = 255)
    private String comentarios;

    @CreationTimestamp
    @Column(name = "fecha_modificacion", nullable = false, updatable = false)
    private LocalDateTime fechaModificacion;

    // Constructores
    public SeguimientoIncidenteEntity() {
    }

    public SeguimientoIncidenteEntity(IncidenteEntity incidente, String estadoAnterior,
                                      String estadoActual, String comentarios) {
        this.incidente = incidente;
        this.estadoAnterior = estadoAnterior;
        this.estadoActual = estadoActual;
        this.comentarios = comentarios;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IncidenteEntity getIncidente() {
        return incidente;
    }

    public void setIncidente(IncidenteEntity incidente) {
        this.incidente = incidente;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "SeguimientoIncidenteEntity{" +
                "id=" + id +
                ", idIncidente=" + (incidente != null ? incidente.getIdIncidente() : null) +
                ", estadoAnterior='" + estadoAnterior + '\'' +
                ", estadoActual='" + estadoActual + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}