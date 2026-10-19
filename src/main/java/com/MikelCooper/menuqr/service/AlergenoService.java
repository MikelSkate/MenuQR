package com.MikelCooper.menuqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.Alergeno;
import com.MikelCooper.menuqr.repository.AlergenoRepository;

/**
 * Servicio que gestiona la lógica de negocio de los alérgenos.
 *
 * <p>Proporciona las operaciones CRUD sobre los alérgenos y la búsqueda
 * por nombre, utilizada al asignar alérgenos a los productos.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class AlergenoService {

    @Autowired
    private AlergenoRepository alergenoRepository;

    /**
     * Obtiene la lista completa de alérgenos almacenados.
     *
     * @return una lista con todos los alérgenos.
     */
    public List<Alergeno> listarAlergenos() {
        return alergenoRepository.findAll();
    }

    /**
     * Guarda un nuevo alérgeno.
     *
     * @param alergeno el alérgeno a guardar.
     * @return el alérgeno guardado con su identificador asignado.
     */
    public Alergeno guardarAlergeno(Alergeno alergeno) {
        // Falta implementar validacion de error
        return alergenoRepository.save(alergeno);
    }

    /**
     * Busca un alérgeno por su identificador.
     *
     * @param id el identificador del alérgeno.
     * @return el alérgeno encontrado.
     * @throws RuntimeException si no existe ningún alérgeno con ese identificador.
     */
    public Alergeno obtenerAlergenoPorId(Long id) {
        return alergenoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alergeno no encontrado")); // Caso si no encuentra al usuario
    }

    /**
     * Actualiza los datos de un alérgeno existente.
     *
     * @param id el identificador del alérgeno a actualizar.
     * @param nuevoAlergeno objeto con los nuevos datos del alérgeno.
     * @return el alérgeno actualizado.
     * @throws RuntimeException si no existe ningún alérgeno con ese identificador.
     */
    public Alergeno actualizarAlergeno(Long id, Alergeno nuevoAlergeno) {
        // Busca el usuario original
        Alergeno alergenoExistente = obtenerAlergenoPorId(id);

        // Actualiza campos
        alergenoExistente.setNombre(nuevoAlergeno.getNombre());
        alergenoExistente.setLinkImagen(nuevoAlergeno.getLinkImagen());

        return alergenoRepository.save(alergenoExistente);
    }

    /**
     * Elimina un alérgeno por su identificador.
     *
     * @param id el identificador del alérgeno a eliminar.
     */
    public void borrarAlergeno(Long id) {
        alergenoRepository.deleteById(id);
    }

    /**
     * Busca un alérgeno por su nombre.
     *
     * @param nombre el nombre del alérgeno.
     * @return el alérgeno encontrado, o {@code null} si no existe.
     */
    public Alergeno obtenerAlergenoPorNombre(String nombre) {
        return alergenoRepository.findByNombre(nombre);
    }
    
}
