package com.MikelCooper.menuqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.Categoria;
import com.MikelCooper.menuqr.repository.CategoriaRepository;

/**
 * Servicio que gestiona la lógica de negocio de las categorías de productos.
 *
 * <p>Proporciona las operaciones CRUD sobre las categorías y sirve de
 * intermediario entre el controlador y el repositorio.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Obtiene la lista completa de categorías almacenadas.
     *
     * @return una lista con todas las categorías.
     */
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Guarda una nueva categoría.
     *
     * @param categoria la categoría a guardar.
     * @return la categoría guardada con su identificador asignado.
     */
    public Categoria guardarCategoria(Categoria categoria) {
        // Falta implementar validacion de error
        return categoriaRepository.save(categoria);
    }

    /**
     * Busca una categoría por su identificador.
     *
     * @param id el identificador de la categoría.
     * @return la categoría encontrada.
     * @throws RuntimeException si no existe ninguna categoría con ese identificador.
     */
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categotia no encontrado")); // Caso si no encuentra al usuario
    }

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param id el identificador de la categoría a actualizar.
     * @param nuevaCategoria objeto con los nuevos datos de la categoría.
     * @return la categoría actualizada.
     * @throws RuntimeException si no existe ninguna categoría con ese identificador.
     */
    public Categoria actualizarCategoria(Long id, Categoria nuevaCategoria) {
        // Busca el usuario original
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        // Actualiza campos
        categoriaExistente.setNombre(nuevaCategoria.getNombre());
        // Guarda categoria
        return categoriaRepository.save(categoriaExistente);
    }

    /**
     * Elimina una categoría por su identificador.
     *
     * @param id el identificador de la categoría a eliminar.
     */
    public void borrarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    /**
     * Busca una categoría por su nombre.
     *
     * @param nombre el nombre de la categoría.
     * @return la categoría encontrada, o {@code null} si no existe.
     */
    public Categoria obtenerCategoriaPorNombre(String nombre) {
    return categoriaRepository.findByNombre(nombre)
        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }
}
