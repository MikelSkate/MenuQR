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
import com.MikelCooper.menuqr.model.Pedido;
import com.MikelCooper.menuqr.service.PedidoService;
 
/**
 * Controlador REST para la gestión de pedidos.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/pedido} y delega
 * la lógica de negocio en {@link PedidoService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
 
    @Autowired
    private PedidoService pedidoService;
 
    /**
     * Obtiene la lista completa de pedidos.
     *
     * @return una lista con todos los pedidos.
     */
    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.listarPedidos();
    }
 
    /**
     * Obtiene un pedido a partir de su identificador.
     *
     * @param id el identificador del pedido.
     * @return el pedido solicitado.
     */
    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }
 
    /**
     * Crea un nuevo pedido.
     *
     * @param pedido el pedido a crear, recibido en el cuerpo de la petición.
     * @return el pedido creado con su identificador asignado.
     */
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }
 
    /**
     * Actualiza un pedido existente.
     *
     * @param id el identificador del pedido a actualizar.
     * @param pedido los nuevos datos del pedido, recibidos en el cuerpo de la petición.
     * @return el pedido actualizado.
     */
    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Long id,
        @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }
 
    /**
     * Elimina un pedido a partir de su identificador.
     *
     * <p>Si el pedido tiene líneas de pedido asociadas que impiden su borrado,
     * se devuelve un estado {@code 409 CONFLICT}.</p>
     *
     * @param id el identificador del pedido a eliminar.
     * @return respuesta {@code 200 OK} si se elimina, o {@code 409 CONFLICT}
     *         si tiene datos asociados.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPedido(@PathVariable Long id) {
        try {
            pedidoService.borrarPedido(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tiene datos asociados");
        }
    }
}
