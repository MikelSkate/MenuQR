package com.MikelCooper.menuqr.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Representa un alérgeno alimentario (por ejemplo: Gluten, Lactosa, Marisco).
 *
 * <p>Cada alérgeno puede estar presente en varios productos. Es el lado inverso
 * de una relación muchos-a-muchos con {@link Producto}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "alergeno")
public class Alergeno {

    /** Identificador único del alérgeno, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del alérgeno. */
    @Column(nullable = false)
    private String nombre;

    /** Representación visual del alérgeno (emoji o ruta de imagen). */
    @Column(nullable = false)
    private String linkImagen;

    /** Productos que contienen este alérgeno. Se ignora en la serialización JSON. */
    @ManyToMany(mappedBy = "alergenos")
    @JsonIgnore
    private List<Producto> productos;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Alergeno() {}

    /**
     * Crea un alérgeno con el nombre y la representación visual indicados.
     *
     * @param nombre el nombre del alérgeno.
     * @param linkImagen la representación visual del alérgeno.
     */
    public Alergeno(String nombre, String linkImagen) {
        this.nombre = nombre;
        this.linkImagen = linkImagen;
    }

    /**
     * Obtiene el identificador del alérgeno.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del alérgeno.
     *
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alérgeno.
     *
     * @param nombre el nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la representación visual del alérgeno.
     *
     * @return el emoji o la ruta de imagen.
     */
    public String getLinkImagen() {
        return linkImagen;
    }

    /**
     * Establece la representación visual del alérgeno.
     *
     * @param linkImagen el nuevo emoji o ruta de imagen.
     */
    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
}
