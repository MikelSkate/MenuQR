package com.MikelCooper.menuqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.model.Pedido;
import com.MikelCooper.menuqr.repository.PedidoRepository;

/**
 * Servicio que gestiona la lógica de negocio de los pedidos.
 *
 * <p>Proporciona las operaciones CRUD sobre los pedidos y el cálculo del
 * importe total de un pedido a partir de sus líneas.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Obtiene la lista completa de pedidos almacenados.
     *
     * @return una lista con todos los pedidos.
     */
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Guarda un nuevo pedido.
     *
     * @param pedido el pedido a guardar.
     * @return el pedido guardado con su identificador asignado.
     */
    public Pedido guardarPedido(Pedido pedido) {
        // Falta implementar validacion de error
        return pedidoRepository.save(pedido);
    }

    /**
     * Busca un pedido por su identificador.
     *
     * @param id el identificador del pedido.
     * @return el pedido encontrado.
     * @throws RuntimeException si no existe ningún pedido con ese identificador.
     */
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado")); // Caso si no encuentra al usuario
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param id el identificador del pedido a actualizar.
     * @param nuevoPedido objeto con los nuevos datos del pedido.
     * @return el pedido actualizado.
     * @throws RuntimeException si no existe ningún pedido con ese identificador.
     */
    public Pedido actualizarPedido(Long id, Pedido nuevoPedido) {
        // Busca el usuario original
        Pedido pedidoExistente = obtenerPedidoPorId(id);

        return pedidoRepository.save(pedidoExistente);
    }

    /**
     * Elimina un pedido por su identificador.
     *
     * @param id el identificador del pedido a eliminar.
     */
    public void borrarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    /**
     * Calcula el importe total de un pedido sumando el subtotal de sus líneas.
     *
     * @param pedido el pedido cuyo total se desea calcular.
     * @return el importe total del pedido.
     */
    public float calcularTotal(Pedido pedido){
        float total = 0;
        for (LineaPedido linea : pedido.getLineas()) {
            total += linea.getCantidad() * linea.getPrecioUnitario();
        }
        return total;
    }
}
