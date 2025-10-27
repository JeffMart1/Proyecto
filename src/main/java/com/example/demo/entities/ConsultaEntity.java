package com.example.demo.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class ConsultaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidente", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_incidente"))
    private IncidenteEntity incidente;

    @Column(name = "tecnico", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (tecnico IN ('pedro', 'felipe', 'juan', 'peter', 'rafael'))")
    private String tecnico;

    @Column(name = "estado", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (estado IN ('abierto', 'en progreso', 'en espera', 'cerrado'))")
    private String estado;

    @Column(name = "prioridad", nullable = false, length = 20,
            columnDefinition = "VARCHAR(20) CHECK (prioridad IN ('alta', 'media', 'baja'))")
    private String prioridad;

    @Column(name = "tiempo_resolucion")
    private Long tiempoResolucion; // Tiempo en minutos

    // Constructores
    public ConsultaEntity() {
    }

    public ConsultaEntity(IncidenteEntity incidente, String tecnico, String estado, String prioridad) {
        this.incidente = incidente;
        this.tecnico = tecnico;
        this.estado = estado;
        this.prioridad = prioridad;
        this.calcularTiempoResolucion();
    }

    // Método para calcular el tiempo de resolución
    public void calcularTiempoResolucion() {
        if (incidente != null && incidente.getFechaCreacion() != null) {
            LocalDateTime ahora = LocalDateTime.now();
            Duration duracion = Duration.between(incidente.getFechaCreacion(), ahora);
            this.tiempoResolucion = duracion.toMinutes();
        }
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
        this.calcularTiempoResolucion();
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Long getTiempoResolucion() {
        return tiempoResolucion;
    }

    public void setTiempoResolucion(Long tiempoResolucion) {
        this.tiempoResolucion = tiempoResolucion;
    }

    @Override
    public String toString() {
        return "ConsultaEntity{" +
                "id=" + id +
                ", idIncidente=" + (incidente != null ? incidente.getIdIncidente() : null) +
                ", tecnico='" + tecnico + '\'' +
                ", estado='" + estado + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", tiempoResolucion=" + tiempoResolucion + " minutos" +
                '}';
    }
}