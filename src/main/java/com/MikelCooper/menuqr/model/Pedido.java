package com.MikelCooper.menuqr.model;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Representa un pedido realizado desde una mesa.
 *
 * <p>Cada pedido está asociado a una {@link Mesa} y se compone de una o varias
 * {@link LineaPedido líneas de pedido}. El estado de entrega del pedido se
 * calcula dinámicamente a partir del estado de todas sus líneas.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    /** Identificador único del pedido, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fecha y hora en que se realizó el pedido. */
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    /** Mesa desde la que se realizó el pedido. Se ignora la propiedad "pedidos" para evitar bucles. */
    @ManyToOne
    @JsonIgnoreProperties(value = {"pedidos"})
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    /** Líneas que componen el pedido. Se ignora la propiedad "pedido" para evitar bucles. */
    @OneToMany(mappedBy = "pedido")
    @JsonIgnoreProperties(value = {"pedido"})
    private List<LineaPedido> lineas;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Pedido() {}

    /**
     * Obtiene el identificador del pedido.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene la fecha y hora del pedido.
     *
     * @return la fecha y hora.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora del pedido.
     *
     * @param fechaHora la nueva fecha y hora.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la mesa asociada al pedido.
     *
     * @return la mesa.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Establece la mesa asociada al pedido.
     *
     * @param mesa la nueva mesa.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Obtiene las líneas que componen el pedido.
     *
     * @return la lista de líneas de pedido.
     */
    public List<LineaPedido> getLineas() {
        return lineas;
    }

    /**
     * Establece las líneas que componen el pedido.
     *
     * @param lineas la nueva lista de líneas de pedido.
     */
    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    /**
     * Calcula si el pedido ha sido entregado por completo.
     *
     * <p>Un pedido se considera entregado únicamente cuando todas sus líneas
     * están marcadas como preparadas. Un pedido sin líneas no se considera
     * entregado. Este valor es calculado y no se persiste en la base de datos.</p>
     *
     * @return {@code true} si todas las líneas están preparadas, {@code false} en caso contrario.
     */
    @Transient
    public boolean isEntregado() {
        if (lineas == null || lineas.isEmpty()) {
            return false;
        }
        return lineas.stream().allMatch(LineaPedido::isListo);
    }
}
