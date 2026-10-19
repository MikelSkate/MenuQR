package com.MikelCooper.menuqr.model;
 
import java.util.List;
import jakarta.persistence.*;
 
/**
 * Representa un producto del menú del restaurante.
 *
 * <p>Cada producto pertenece a una {@link Categoria}, puede contener varios
 * {@link Alergeno alérgenos} y se prepara en un destino concreto
 * ({@code BAR} o {@code COCINA}).</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Entity
@Table(name = "producto")
public class Producto {
 
    /** Identificador único del producto, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    /** Nombre del producto. */
    @Column(nullable = false)
    private String nombre;
 
    /** Precio del producto. */
    @Column(nullable = false)
    private Float precio;
 
    /** Enlace o ruta de la imagen del producto. */
    @Column(nullable = false)
    private String linkImagen;
 
    /** Descripción del producto. */
    @Column(nullable = false)
    private String descripcion;
 
    /** Destino de preparación del producto ({@code BAR} o {@code COCINA}). */
    @Column(nullable = false)
    private String destino;
 
    /** Categoría a la que pertenece el producto. */
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
 
    /** Lista de alérgenos asociados al producto. */
    @ManyToMany
    @JoinTable(name = "producto_alergeno")
    private List<Alergeno> alergenos;
 
    /**
     * Constructor vacío requerido por JPA.
     */
    public Producto() {}
 
    /**
     * Crea un producto con los datos básicos indicados.
     *
     * @param nombre el nombre del producto.
     * @param precio el precio del producto.
     * @param linkImagen el enlace o ruta de la imagen.
     * @param descripcion la descripción del producto.
     * @param destino el destino de preparación ({@code BAR} o {@code COCINA}).
     */
    public Producto(String nombre, Float precio, String linkImagen, String descripcion, String destino) {
        this.nombre = nombre;
        this.precio = precio;
        this.linkImagen = linkImagen;
        this.descripcion = descripcion;
        this.destino = destino;
    }
 
    /**
     * Obtiene el identificador del producto.
     *
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }
 
    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }
 
    /**
     * Establece el nombre del producto.
     *
     * @param nombre el nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    /**
     * Obtiene el precio del producto.
     *
     * @return el precio.
     */
    public Float getPrecio() {
        return precio;
    }
 
    /**
     * Establece el precio del producto.
     *
     * @param precio el nuevo precio.
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
 
    /**
     * Obtiene el enlace de la imagen del producto.
     *
     * @return el enlace o ruta de la imagen.
     */
    public String getLinkImagen() {
        return linkImagen;
    }
 
    /**
     * Establece el enlace de la imagen del producto.
     *
     * @param linkImagen el nuevo enlace o ruta de la imagen.
     */
    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
 
    /**
     * Obtiene la descripción del producto.
     *
     * @return la descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }
 
    /**
     * Establece la descripción del producto.
     *
     * @param descripcion la nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    /**
     * Obtiene el destino de preparación del producto.
     *
     * @return el destino ({@code BAR} o {@code COCINA}).
     */
    public String getDestino() {
        return destino;
    }
 
    /**
     * Establece el destino de preparación del producto.
     *
     * @param destino el nuevo destino ({@code BAR} o {@code COCINA}).
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }
 
    /**
     * Obtiene la categoría del producto.
     *
     * @return la categoría.
     */
    public Categoria getCategoria() {
        return categoria;
    }
 
    /**
     * Establece la categoría del producto.
     *
     * @param categoria la nueva categoría.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
 
    /**
     * Obtiene la lista de alérgenos del producto.
     *
     * @return la lista de alérgenos.
     */
    public List<Alergeno> getAlergenos() {
        return alergenos;
    }
 
    /**
     * Establece la lista de alérgenos del producto.
     *
     * @param alergenos la nueva lista de alérgenos.
     */
    public void setAlergenos(List<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }
}
