package com.MikelCooper.menuqr.util;

import org.springframework.stereotype.Component;
import com.MikelCooper.menuqr.service.LineaPedidoService;
import com.MikelCooper.menuqr.service.PedidoService;
import com.MikelCooper.menuqr.service.ProductoService;
import com.MikelCooper.menuqr.config.InitializationConfig;
import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.model.Pedido;
import com.MikelCooper.menuqr.model.Producto;
import java.util.List;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

/**
 * Inicializador que genera líneas de pedido aleatorias en la base de datos.
 *
 * <p>Para cada pedido existente crea un número aleatorio de líneas, cada una
 * con un producto aleatorio, una cantidad aleatoria, su precio unitario y un
 * estado de preparación también aleatorio.</p>
 *
 * <p>Requiere que los pedidos y los productos ya estén cargados en la base de
 * datos antes de ejecutarse.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class LineaPedidoInitializer {

    @Autowired
    private InitializationConfig config;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private LineaPedidoService lineaPedidoService;

    /**
     * Crea y guarda líneas de pedido aleatorias para cada pedido existente.
     *
     * <p>El número de líneas por pedido, el producto, la cantidad y el estado de
     * preparación se generan de forma aleatoria con Java Faker.</p>
     */
    @Transactional
    public void inicializarLineaPedidos() {

        Faker faker = new Faker();

        List<Pedido> pedidos = pedidoService.listarPedidos();

        // Recorre todos los pedidos
        for (Pedido pedido : pedidos) {
            int numeroAleatorio = faker.number().numberBetween(1, config.getMaximoDeLineasDePedido());
            // Crea un numero aleatorio de lineas de pedidos para cada pedido
            for (int j = 0; j < numeroAleatorio; j++) {
                // Obtenemos un producto aleatorio
                List<Producto> productos = productoService.listarProductos();
                Producto productoAleatoria = productos.get(faker.number().numberBetween(0, productos.size()));
                // Creamos linea de pedido
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setListo(faker.bool().bool());
                lineaPedido.setProducto(productoAleatoria);
                lineaPedido.setPedido(pedido);
                lineaPedido.setCantidad(faker.number().numberBetween(1, config.getMaximaCantidadDeProductosPorLinea()));
                lineaPedido.setPrecioUnitario(productoAleatoria.getPrecio());
                lineaPedidoService.guardarLineaPedido(lineaPedido);
            }
        }
    }
}