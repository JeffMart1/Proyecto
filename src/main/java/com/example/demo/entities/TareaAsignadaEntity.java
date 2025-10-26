package com.example.demo.entities;

import com.jdc.web.enums.EstadoIncidente;
import com.jdc.web.enums.PrioridadIncidente;
import com.jdc.web.enums.TecnicoAsignado;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas_asignadas")
public class TareaAsignadaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidente", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tarea_incidente"))
    private IncidenteEntity incidente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tecnico", nullable = false, length = 20)
    private TecnicoAsignado tecnico;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoIncidente estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad", nullable = false, length = 20)
    private PrioridadIncidente prioridad;

    @Column(name = "tiempo_resolucion")
    private Long tiempoResolucion; // En minutos

    // Constructores
    public TareaAsignadaEntity() {
    }

    public TareaAsignadaEntity(IncidenteEntity incidente, TecnicoAsignado tecnico,
                               EstadoIncidente estado, PrioridadIncidente prioridad) {
        this.incidente = incidente;
        this.tecnico = tecnico;
        this.estado = estado;
        this.prioridad = prioridad;
        this.calcularTiempoResolucion();
    }

    // Método auxiliar para calcular tiempo de resolución
    public void calcularTiempoResolucion() {
        if (this.incidente != null && this.incidente.getFechaCreacion() != null) {
            LocalDateTime fechaCreacion = this.incidente.getFechaCreacion();
            LocalDateTime ahora = LocalDateTime.now();
            Duration duracion = Duration.between(fechaCreacion, ahora);
            this.tiempoResolucion = duracion.toMinutes();
        }
    }

    // Método de callback para actualizar tiempo antes de persistir/actualizar
    @PrePersist
    @PreUpdate
    public void actualizarTiempoResolucion() {
        this.calcularTiempoResolucion();
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

    public TecnicoAsignado getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoAsignado tecnico) {
        this.tecnico = tecnico;
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

    public Long getTiempoResolucion() {
        return tiempoResolucion;
    }

    public void setTiempoResolucion(Long tiempoResolucion) {
        this.tiempoResolucion = tiempoResolucion;
    }

    // Método auxiliar para obtener tiempo en formato legible
    public String getTiempoResolucionFormateado() {
        if (tiempoResolucion == null) {
            return "N/A";
        }

        long horas = tiempoResolucion / 60;
        long minutos = tiempoResolucion % 60;

        if (horas > 24) {
            long dias = horas / 24;
            horas = horas % 24;
            return String.format("%d días, %d horas, %d minutos", dias, horas, minutos);
        } else if (horas > 0) {
            return String.format("%d horas, %d minutos", horas, minutos);
        } else {
            return String.format("%d minutos", minutos);
        }
    }

    @Override
    public String toString() {
        return "TareaAsignadaEntity{" +
                "id=" + id +
                ", tecnico=" + tecnico +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", tiempoResolucion=" + tiempoResolucion + " minutos" +
                '}';
    }
}