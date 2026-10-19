package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.model.Pedido;
import com.MikelCooper.menuqr.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias del servicio {@link PedidoService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia. Incluye la verificación del
 * cálculo del total a partir de las líneas del pedido.</p>
 */
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void inicializarPedido() {
        pedido = new Pedido();
    }

    @Test
    void listarPedidos_devuelveListaCompleta_siExistenPedidos() {
        // Arrange
        List<Pedido> pedidos = Arrays.asList(pedido, new Pedido());
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        // Act
        List<Pedido> resultado = pedidoService.listarPedidos();

        // Assert
        assertEquals(2, resultado.size());
        verify(pedidoRepository).findAll();
    }

    @Test
    void guardarPedido_devuelvePedidoGuardado_siEsCorrecto() {
        // Arrange
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        // Act
        Pedido resultado = pedidoService.guardarPedido(pedido);

        // Assert
        assertNotNull(resultado);
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void obtenerPedidoPorId_devuelvePedido_siExiste() {
        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        Pedido resultado = pedidoService.obtenerPedidoPorId(1L);

        // Assert
        assertNotNull(resultado);
    }

    @Test
    void obtenerPedidoPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> pedidoService.obtenerPedidoPorId(99L));
    }

    @Test
    void calcularTotal_devuelveSumaCorrecta_siHayVariasLineas() {
        // Arrange
        LineaPedido linea1 = new LineaPedido();
        linea1.setCantidad(2);
        linea1.setPrecioUnitario(5.0f);

        LineaPedido linea2 = new LineaPedido();
        linea2.setCantidad(3);
        linea2.setPrecioUnitario(4.0f);

        pedido.setLineas(Arrays.asList(linea1, linea2));

        // Act
        float total = pedidoService.calcularTotal(pedido);

        // Assert
        assertEquals(22.0f, total); // (2*5) + (3*4) = 22
    }

    @Test
    void calcularTotal_devuelveCero_siNoHayLineas() {
        // Arrange
        pedido.setLineas(Arrays.asList());

        // Act
        float total = pedidoService.calcularTotal(pedido);

        // Assert
        assertEquals(0.0f, total);
    }

    @Test
    void borrarPedido_invocaDeleteById_siSeLlama() {
        // Act
        pedidoService.borrarPedido(1L);

        // Assert
        verify(pedidoRepository).deleteById(1L);
    }
}
