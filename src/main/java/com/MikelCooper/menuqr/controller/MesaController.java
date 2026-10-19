
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
import com.MikelCooper.menuqr.model.Mesa;
import com.MikelCooper.menuqr.service.MesaService;
 
/**
 * Controlador REST para la gestión de mesas del restaurante.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/mesa}, además de la
 * búsqueda por número de mesa. Delega la lógica de negocio en {@link MesaService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mesa")
public class MesaController {
 
    @Autowired
    private MesaService mesaService;
 
    /**
     * Obtiene la lista completa de mesas.
     *
     * @return una lista con todas las mesas.
     */
    @GetMapping
    public List<Mesa> obtenerTodos() {
        return mesaService.listarMesas();
    }
 
    /**
     * Obtiene una mesa a partir de su identificador.
     *
     * @param id el identificador de la mesa.
     * @return la mesa solicitada.
     */
    @GetMapping("/{id}")
    public Mesa obtenerPorId(@PathVariable Long id) {
        return mesaService.obtenerMesaPorId(id);
    }
 
    /**
     * Crea una nueva mesa.
     *
     * @param mesa la mesa a crear, recibida en el cuerpo de la petición.
     * @return la mesa creada con su identificador asignado.
     */
    @PostMapping
    public Mesa crearMesa(@RequestBody Mesa mesa) {
        return mesaService.guardarMesa(mesa);
    }
 
    /**
     * Actualiza una mesa existente.
     *
     * @param id el identificador de la mesa a actualizar.
     * @param mesa los nuevos datos de la mesa, recibidos en el cuerpo de la petición.
     * @return la mesa actualizada.
     */
    @PutMapping("/{id}")
    public Mesa actualizarMesa(@PathVariable Long id,
        @RequestBody Mesa mesa) {
        return mesaService.actualizarMesa(id, mesa);
    }
 
    /**
     * Elimina una mesa a partir de su identificador.
     *
     * <p>Si la mesa tiene pedidos asociados que impiden su borrado, se
     * devuelve un estado {@code 409 CONFLICT}.</p>
     *
     * @param id el identificador de la mesa a eliminar.
     * @return respuesta {@code 200 OK} si se elimina, o {@code 409 CONFLICT}
     *         si tiene datos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarMesa(@PathVariable Long id) {
        try {
            mesaService.borrarMesa(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tiene datos asociados");
        }
    }
 
    /**
     * Obtiene una mesa a partir de su número.
     *
     * @param numero el número de la mesa.
     * @return la mesa correspondiente al número indicado.
     */
    @GetMapping("/numero/{numero}")
    public Mesa obtenerPorNumero(@PathVariable int numero) {
        return mesaService.obtenerMesaPorNumero(numero);
    }
}