package com.MikelCooper.menuqr.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.service.LineaPedidoService;
 
/**
 * Controlador REST para la gestión de líneas de pedido.
 *
 * <p>Expone los endpoints CRUD bajo la ruta {@code /api/lineapedido}, además
 * del filtrado por destino y estado, y el marcado de líneas como preparadas.
 * Delega la lógica de negocio en {@link LineaPedidoService}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/lineapedido")
public class LineaPedidoController {
 
    @Autowired
    private LineaPedidoService lineaPedidoService;
 
    /**
     * Obtiene la lista completa de líneas de pedido.
     *
     * @return una lista con todas las líneas de pedido.
     */
    @GetMapping
    public List<LineaPedido> obtenerTodos() {
        return lineaPedidoService.listarLineasPedido();
    }
 
    /**
     * Obtiene una línea de pedido a partir de su identificador.
     *
     * @param id el identificador de la línea de pedido.
     * @return la línea de pedido solicitada.
     */
    @GetMapping("/{id}")
    public LineaPedido obtenerPorId(@PathVariable Long id) {
        return lineaPedidoService.obtenerLineaPedidoPorId(id);
    }
 
    /**
     * Crea una nueva línea de pedido.
     *
     * @param lineaPedido la línea de pedido a crear, recibida en el cuerpo de la petición.
     * @return la línea de pedido creada con su identificador asignado.
     */
    @PostMapping
    public LineaPedido crearLineaPedido(@RequestBody LineaPedido lineaPedido) {
        return lineaPedidoService.guardarLineaPedido(lineaPedido);
    }
 
    /**
     * Elimina una línea de pedido a partir de su identificador.
     *
     * @param id el identificador de la línea de pedido a eliminar.
     */
    @DeleteMapping("/{id}")
    public void borrarLineaPedido(@PathVariable Long id) {
        lineaPedidoService.borrarLineaPedido(id);
    }
 
    /**
     * Actualiza una línea de pedido existente.
     *
     * @param id el identificador de la línea de pedido a actualizar.
     * @param lineaPedido los nuevos datos de la línea, recibidos en el cuerpo de la petición.
     * @return la línea de pedido actualizada.
     */
    @PutMapping("/{id}")
    public LineaPedido actualizarLineaPedido(@PathVariable Long id,
        @RequestBody LineaPedido lineaPedido) {
        return lineaPedidoService.actualizarLineaPedido(id, lineaPedido);
    }
 
    /**
     * Obtiene las líneas de pedido filtradas por destino y estado de preparación.
     *
     * @param destino el destino del producto ({@code BAR} o {@code COCINA}).
     * @param listo {@code true} para líneas preparadas, {@code false} para pendientes.
     * @return una lista con las líneas de pedido que cumplen ambos criterios.
     */
    @GetMapping("/destino/{destino}/listo/{listo}")
    public List<LineaPedido> obtenerPorDestinoYListo(@PathVariable String destino,
        @PathVariable boolean listo) {
        return lineaPedidoService.obtenerPorDestinoYListo(destino, listo);
    }
 
    /**
     * Marca una línea de pedido como preparada.
     *
     * @param id el identificador de la línea de pedido a marcar como lista.
     */
    @PatchMapping("/{id}/listo")
    public void marcarListo(@PathVariable Long id) {
        lineaPedidoService.marcarListo(id);
    }
}