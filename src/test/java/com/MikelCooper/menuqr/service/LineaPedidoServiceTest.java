package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.LineaPedido;
import com.MikelCooper.menuqr.repository.LineaPedidoRepository;
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
 * Pruebas unitarias del servicio {@link LineaPedidoService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia. Incluye la verificación del
 * cambio de estado de preparación de una línea.</p>
 */
@ExtendWith(MockitoExtension.class)
class LineaPedidoServiceTest {

    @Mock
    private LineaPedidoRepository lineaPedidoRepository;

    @InjectMocks
    private LineaPedidoService lineaPedidoService;

    private LineaPedido linea;

    @BeforeEach
    void inicializarLinea() {
        linea = new LineaPedido();
        linea.setCantidad(2);
        linea.setPrecioUnitario(8.5f);
        linea.setListo(false);
    }

    @Test
    void listarLineasPedido_devuelveListaCompleta_siExistenLineas() {
        // Arrange
        List<LineaPedido> lineas = Arrays.asList(linea, new LineaPedido());
        when(lineaPedidoRepository.findAll()).thenReturn(lineas);

        // Act
        List<LineaPedido> resultado = lineaPedidoService.listarLineasPedido();

        // Assert
        assertEquals(2, resultado.size());
        verify(lineaPedidoRepository).findAll();
    }

    @Test
    void guardarLineaPedido_devuelveLineaGuardada_siEsCorrecta() {
        // Arrange
        when(lineaPedidoRepository.save(linea)).thenReturn(linea);

        // Act
        LineaPedido resultado = lineaPedidoService.guardarLineaPedido(linea);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.getCantidad());
        verify(lineaPedidoRepository).save(linea);
    }

    @Test
    void obtenerLineaPedidoPorId_devuelveLinea_siExiste() {
        // Arrange
        when(lineaPedidoRepository.findById(1L)).thenReturn(Optional.of(linea));

        // Act
        LineaPedido resultado = lineaPedidoService.obtenerLineaPedidoPorId(1L);

        // Assert
        assertEquals(8.5f, resultado.getPrecioUnitario());
    }

    @Test
    void obtenerLineaPedidoPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(lineaPedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> lineaPedidoService.obtenerLineaPedidoPorId(99L));
    }

    @Test
    void actualizarLineaPedido_actualizaCantidadYEstado_siExiste() {
        // Arrange
        LineaPedido nuevosDatos = new LineaPedido();
        nuevosDatos.setCantidad(5);
        nuevosDatos.setListo(true);
        when(lineaPedidoRepository.findById(1L)).thenReturn(Optional.of(linea));
        when(lineaPedidoRepository.save(linea)).thenReturn(linea);

        // Act
        LineaPedido resultado = lineaPedidoService.actualizarLineaPedido(1L, nuevosDatos);

        // Assert
        assertEquals(5, resultado.getCantidad());
        assertTrue(resultado.isListo());
    }

    @Test
    void marcarListo_poneLineaComoLista_siExiste() {
        // Arrange
        when(lineaPedidoRepository.findById(1L)).thenReturn(Optional.of(linea));

        // Act
        lineaPedidoService.marcarListo(1L);

        // Assert
        assertTrue(linea.isListo());
        verify(lineaPedidoRepository).save(linea);
    }

    @Test
    void obtenerPorDestinoYListo_devuelveLineasFiltradas_siCoinciden() {
        // Arrange
        List<LineaPedido> lineas = Arrays.asList(linea);
        when(lineaPedidoRepository.findByProductoDestinoAndListo("COCINA", false))
            .thenReturn(lineas);

        // Act
        List<LineaPedido> resultado =
            lineaPedidoService.obtenerPorDestinoYListo("COCINA", false);

        // Assert
        assertEquals(1, resultado.size());
        verify(lineaPedidoRepository).findByProductoDestinoAndListo("COCINA", false);
    }

    @Test
    void borrarLineaPedido_invocaDeleteById_siSeLlama() {
        // Act
        lineaPedidoService.borrarLineaPedido(1L);

        // Assert
        verify(lineaPedidoRepository).deleteById(1L);
    }
}
