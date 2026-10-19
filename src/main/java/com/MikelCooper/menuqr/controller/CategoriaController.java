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
import com.MikelCooper.menuqr.model.Categoria;
import com.MikelCooper.menuqr.service.CategoriaService;
 
/**
 * Controlador REST para la gestión de categorías de productos.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/categoria} y delega
 * la lógica de negocio en {@link CategoriaService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
 
    @Autowired
    private CategoriaService categoriaService;
 
    /**
     * Obtiene la lista completa de categorías.
     *
     * @return una lista con todas las categorías.
     */
    @GetMapping
    public List<Categoria> obtenerTodos() {
        return categoriaService.listarCategorias();
    }
 
    /**
     * Obtiene una categoría a partir de su identificador.
     *
     * @param id el identificador de la categoría.
     * @return la categoría solicitada.
     */
    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }
 
    /**
     * Crea una nueva categoría.
     *
     * @param categoria la categoría a crear, recibida en el cuerpo de la petición.
     * @return la categoría creada con su identificador asignado.
     */
    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }
 
    /**
     * Actualiza una categoría existente.
     *
     * @param id el identificador de la categoría a actualizar.
     * @param categoria los nuevos datos de la categoría, recibidos en el cuerpo de la petición.
     * @return la categoría actualizada.
     */
    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id,
        @RequestBody Categoria categoria) {
        return categoriaService.actualizarCategoria(id, categoria);
    }
 
    /**
     * Elimina una categoría a partir de su identificador.
     *
     * <p>Si la categoría tiene productos asociados que impiden su borrado, se
     * devuelve un estado {@code 409 CONFLICT}.</p>
     *
     * @param id el identificador de la categoría a eliminar.
     * @return respuesta {@code 200 OK} si se elimina, o {@code 409 CONFLICT}
     *         si tiene datos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable Long id) {
        try {
            categoriaService.borrarCategoria(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tiene datos asociados");
        }
    }
}
