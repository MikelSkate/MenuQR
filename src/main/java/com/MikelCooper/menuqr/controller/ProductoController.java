
package com.MikelCooper.menuqr.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.MikelCooper.menuqr.model.Producto;
import com.MikelCooper.menuqr.service.ProductoService;
 
/**
 * Controlador REST para la gestión de productos del menú.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/producto} y delega
 * la lógica de negocio en {@link ProductoService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/producto")
public class ProductoController {
 
    @Autowired
    private ProductoService productoService;
 
    /**
     * Obtiene la lista completa de productos.
     *
     * @return una lista con todos los productos.
     */
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.listarProductos();
    }
 
    /**
     * Obtiene un producto a partir de su identificador.
     *
     * @param id el identificador del producto.
     * @return el producto solicitado.
     */
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }
 
    /**
     * Crea un nuevo producto.
     *
     * @param producto el producto a crear, recibido en el cuerpo de la petición.
     * @return el producto creado con su identificador asignado.
     */
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
 
    /**
     * Actualiza un producto existente.
     *
     * @param id el identificador del producto a actualizar.
     * @param producto los nuevos datos del producto, recibidos en el cuerpo de la petición.
     * @return el producto actualizado.
     */
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id,
        @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }
 
    /**
     * Elimina un producto a partir de su identificador.
     *
     * <p>Si el producto tiene datos asociados que impiden su borrado, se
     * devuelve un estado {@code 409 CONFLICT}.</p>
     *
     * @param id el identificador del producto a eliminar.
     * @return respuesta {@code 200 OK} si se elimina, o {@code 409 CONFLICT}
     *         si tiene datos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        try {
            productoService.borrarProducto(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tiene datos asociados");
        }
    }
}
