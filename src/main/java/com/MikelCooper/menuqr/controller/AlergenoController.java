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
import com.MikelCooper.menuqr.model.Alergeno;
import com.MikelCooper.menuqr.service.AlergenoService;
import java.util.List;
 
/**
 * Controlador REST para la gestión de alérgenos.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/alergeno} y delega
 * la lógica de negocio en {@link AlergenoService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alergeno")
public class AlergenoController {
 
    @Autowired
    private AlergenoService alergenoService;
 
    /**
     * Obtiene la lista completa de alérgenos.
     *
     * @return una lista con todos los alérgenos.
     */
    @GetMapping
    public List<Alergeno> obtenerTodos() {
        return alergenoService.listarAlergenos();
    }
 
    /**
     * Obtiene un alérgeno a partir de su identificador.
     *
     * @param id el identificador del alérgeno.
     * @return el alérgeno solicitado.
     */
    @GetMapping("/{id}")
    public Alergeno obtenerPorId(@PathVariable Long id) {
        return alergenoService.obtenerAlergenoPorId(id);
    }
 
    /**
     * Crea un nuevo alérgeno.
     *
     * @param alergeno el alérgeno a crear, recibido en el cuerpo de la petición.
     * @return el alérgeno creado con su identificador asignado.
     */
    @PostMapping
    public Alergeno crearAlergeno(@RequestBody Alergeno alergeno) {
        return alergenoService.guardarAlergeno(alergeno);
    }
 
    /**
     * Actualiza un alérgeno existente.
     *
     * @param id el identificador del alérgeno a actualizar.
     * @param alergeno los nuevos datos del alérgeno, recibidos en el cuerpo de la petición.
     * @return el alérgeno actualizado.
     */
    @PutMapping("/{id}")
    public Alergeno actualizarAlergeno(@PathVariable Long id,
        @RequestBody Alergeno alergeno) {
        return alergenoService.actualizarAlergeno(id, alergeno);
    }
 
    /**
     * Elimina un alérgeno a partir de su identificador.
     *
     * <p>Si el alérgeno tiene productos asociados que impiden su borrado, se
     * devuelve un estado {@code 409 CONFLICT}.</p>
     *
     * @param id el identificador del alérgeno a eliminar.
     * @return respuesta {@code 200 OK} si se elimina, o {@code 409 CONFLICT}
     *         si tiene datos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAlergeno(@PathVariable Long id) {
        try {
            alergenoService.borrarAlergeno(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tiene datos asociados");
        }
    }
}
