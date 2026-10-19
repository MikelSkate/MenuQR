package com.MikelCooper.menuqr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Representa una línea dentro de un pedido.
 *
 * <p>Cada línea de pedido vincula un {@link Producto} concreto con un
 * {@link Pedido}, indicando la cantidad solicitada, el precio unitario en el
 * momento del pedido y si ya ha sido preparada.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "lineapedido")
public class LineaPedido {

    /** Identificador único de la línea de pedido, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Cantidad de unidades del producto en esta línea. */
    @Column(nullable = false)
    private Integer cantidad;

    /** Precio unitario del producto en el momento del pedido. */
    @Column(nullable = false)
    private Float precioUnitario;

    /** Indica si la línea ya ha sido preparada. */
    @Column(nullable = false)
    private boolean listo;

    /** Producto asociado a esta línea de pedido. */
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /** Pedido al que pertenece esta línea. Se ignora la propiedad "lineas" para evitar bucles. */
    @ManyToOne
    @JsonIgnoreProperties(value = {"lineas"})
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Constructor vacío requerido por JPA.
     */
    public LineaPedido() {}

    /**
     * Crea una línea de pedido con la cantidad y el precio unitario indicados.
     *
     * @param cantidad la cantidad de unidades.
     * @param precioUnitario el precio unitario del producto.
     */
    public LineaPedido(Integer cantidad, Float precioUnitario) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el identificador de la línea de pedido.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene la cantidad de unidades de la línea.
     *
     * @return la cantidad.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades de la línea.
     *
     * @param cantidad la nueva cantidad.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return el precio unitario.
     */
    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto.
     *
     * @param precioUnitario el nuevo precio unitario.
     */
    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el producto asociado a la línea.
     *
     * @return el producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a la línea.
     *
     * @param producto el nuevo producto.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el pedido al que pertenece la línea.
     *
     * @return el pedido.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido al que pertenece la línea.
     *
     * @param pedido el nuevo pedido.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Indica si la línea ya ha sido preparada.
     *
     * @return {@code true} si está preparada, {@code false} en caso contrario.
     */
    public boolean isListo() {
        return listo;
    }

    /**
     * Establece el estado de preparación de la línea.
     *
     * @param listo {@code true} para marcarla como preparada.
     */
    public void setListo(boolean listo) {
        this.listo = listo;
    }
}