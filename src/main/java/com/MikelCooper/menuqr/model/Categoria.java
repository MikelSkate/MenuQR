package com.MikelCooper.menuqr.model;

import jakarta.persistence.*;

/**
 * Representa una categoría del menú (por ejemplo: Postres, Entrantes, Bebidas).
 *
 * <p>Cada categoría agrupa varios productos. Es el lado inverso de una
 * relación uno-a-muchos con {@link Producto}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "categoria")
public class Categoria {

    /** Identificador único de la categoría, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la categoría. */
    @Column(nullable = false)
    private String nombre;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Categoria() {}

    /**
     * Crea una categoría con el nombre indicado.
     *
     * @param nombre el nombre de la categoría.
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombre el nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
