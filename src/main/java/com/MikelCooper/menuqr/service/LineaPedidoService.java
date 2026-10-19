package com.MikelCooper.menuqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.repository.LineaPedidoRepository;

/**
 * Servicio que gestiona la lógica de negocio de las líneas de pedido.
 *
 * <p>Cada línea de pedido representa un producto concreto dentro de un pedido,
 * con su cantidad y estado de preparación. Este servicio ofrece las operaciones
 * CRUD y métodos para consultar y actualizar el estado de preparación.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class LineaPedidoService {

    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;

    /**
     * Obtiene la lista completa de líneas de pedido almacenadas.
     *
     * @return una lista con todas las líneas de pedido.
     */
    public List<LineaPedido> listarLineasPedido() {
        return lineaPedidoRepository.findAll();
    }

    /**
     * Guarda una nueva línea de pedido.
     *
     * @param lineaPedido la línea de pedido a guardar.
     * @return la línea de pedido guardada con su identificador asignado.
     */
    public LineaPedido guardarLineaPedido(LineaPedido lineaPedido) {
        // Falta implementar validacion de error
        return lineaPedidoRepository.save(lineaPedido);
    }

    /**
     * Busca una línea de pedido por su identificador.
     *
     * @param id el identificador de la línea de pedido.
     * @return la línea de pedido encontrada.
     * @throws RuntimeException si no existe ninguna línea con ese identificador.
     */
    public LineaPedido obtenerLineaPedidoPorId(Long id) {
        return lineaPedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Linea de pedido no encontrada")); // Caso si no encuentra al usuario
    }

    /**
     * Elimina una línea de pedido por su identificador.
     *
     * @param id el identificador de la línea de pedido a eliminar.
     */
    public void borrarLineaPedido(Long id) {
        lineaPedidoRepository.deleteById(id);
    }    

    /**
     * Obtiene las líneas de pedido filtradas por el destino de su producto
     * y por su estado de preparación.
     *
     * @param destino el destino del producto ({@code BAR} o {@code COCINA}).
     * @param listo {@code true} para líneas ya preparadas, {@code false} para pendientes.
     * @return una lista con las líneas de pedido que cumplen ambos criterios.
     */
    public List<LineaPedido> obtenerPorDestinoYListo(String destino, boolean listo) {
        return lineaPedidoRepository.findByProductoDestinoAndListo(destino, listo);
    }

    /**
     * Actualiza la cantidad y el estado de preparación de una línea de pedido.
     *
     * @param id el identificador de la línea de pedido a actualizar.
     * @param lineaPedido objeto con los nuevos datos de la línea.
     * @return la línea de pedido actualizada.
     * @throws RuntimeException si no existe ninguna línea con ese identificador.
     */
    public LineaPedido actualizarLineaPedido(Long id, LineaPedido lineaPedido) {
        LineaPedido lineaExistente = obtenerLineaPedidoPorId(id);
        lineaExistente.setCantidad(lineaPedido.getCantidad());
        lineaExistente.setListo(lineaPedido.isListo());
        return lineaPedidoRepository.save(lineaExistente);
    }

    /**
     * Marca una línea de pedido como preparada.
     *
     * @param id el identificador de la línea de pedido a marcar como lista.
     * @throws RuntimeException si no existe ninguna línea con ese identificador.
     */
    public void marcarListo(Long id) {
        LineaPedido linea = obtenerLineaPedidoPorId(id);
        linea.setListo(true);
        lineaPedidoRepository.save(linea);
    }
}
