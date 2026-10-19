package com.MikelCooper.menuqr.model;

import jakarta.persistence.*;

/**
 * Representa una mesa del restaurante.
 *
 * <p>Cada mesa tiene un número identificativo y un código QR asociado que los
 * clientes escanean para acceder al menú y realizar pedidos.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "mesa")
public class Mesa {

    /** Identificador único de la mesa, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Número visible de la mesa en el restaurante. */
    @Column(nullable = false)
    private Integer numero;

    /** Código QR asociado a la mesa. */
    @Column(nullable = false)
    private String mesaQr;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Mesa() {}

    /**
     * Crea una mesa con el número y el código QR indicados.
     *
     * @param numero el número de la mesa.
     * @param mesaQr el código QR de la mesa.
     */
    public Mesa(Integer numero, String mesaQr) {
        this.numero = numero;
        this.mesaQr = mesaQr;
    }

    /**
     * Obtiene el identificador de la mesa.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el número de la mesa.
     *
     * @return el número.
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Establece el número de la mesa.
     *
     * @param numero el nuevo número.
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el código QR de la mesa.
     *
     * @return el código QR.
     */
    public String getMesaQr() {
        return mesaQr;
    }

    /**
     * Establece el código QR de la mesa.
     *
     * @param mesaQr el nuevo código QR.
     */
    public void setMesaQr(String mesaQr) {
        this.mesaQr = mesaQr;
    }
}
