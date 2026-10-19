
package com.MikelCooper.menuqr.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.Producto;
import com.MikelCooper.menuqr.repository.ProductoRepository;
 
/**
 * Servicio que gestiona la lógica de negocio de los productos del menú.
 *
 * <p>Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de las operaciones CRUD sobre los productos y de validar
 * los datos antes de persistirlos.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class ProductoService {
 
    @Autowired
    private ProductoRepository productoRepository;
 
    /**
     * Obtiene la lista completa de productos almacenados.
     *
     * @return una lista con todos los productos.
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
 
    /**
     * Guarda un nuevo producto validando que su destino sea correcto.
     *
     * @param producto el producto a guardar.
     * @return el producto guardado con su identificador asignado.
     * @throws IllegalArgumentException si el destino no es {@code BAR} ni {@code COCINA}.
     */
    public Producto guardarProducto(Producto producto) {
        String destino = producto.getDestino();
        if (!destino.equals("BAR") && !destino.equals("COCINA")) {
            throw new IllegalArgumentException("Destino no válido, debe ser BAR o COCINA");
        }
        return productoRepository.save(producto);
    }
 
    /**
     * Busca un producto por su identificador.
     *
     * @param id el identificador del producto.
     * @return el producto encontrado.
     * @throws RuntimeException si no existe ningún producto con ese identificador.
     */
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
 
    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id el identificador del producto a actualizar.
     * @param nuevoProducto objeto con los nuevos datos del producto.
     * @return el producto actualizado.
     * @throws RuntimeException si no existe ningún producto con ese identificador.
     */
    public Producto actualizarProducto(Long id, Producto nuevoProducto) {
        Producto productoExistente = obtenerProductoPorId(id);
 
        productoExistente.setNombre(nuevoProducto.getNombre());
        productoExistente.setPrecio(nuevoProducto.getPrecio());
        productoExistente.setLinkImagen(nuevoProducto.getLinkImagen());
        productoExistente.setDescripcion(nuevoProducto.getDescripcion());
        productoExistente.setDestino(nuevoProducto.getDestino());
        productoExistente.setCategoria(nuevoProducto.getCategoria());
        productoExistente.setAlergenos(nuevoProducto.getAlergenos());
 
        return productoRepository.save(productoExistente);
    }
 
    /**
     * Elimina un producto por su identificador.
     *
     * @param id el identificador del producto a eliminar.
     */
    public void borrarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}