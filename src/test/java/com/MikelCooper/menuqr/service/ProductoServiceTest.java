package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.Producto;
import com.MikelCooper.menuqr.repository.ProductoRepository;
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
 * Pruebas unitarias del servicio {@link ProductoService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia.</p>
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void inicializarProducto() {
        producto = new Producto();
        producto.setNombre("Paella valenciana");
        producto.setPrecio(14.00f);
        producto.setDestino("COCINA");
    }

    @Test
    void listarProductos_devuelveListaCompleta_siExistenProductos() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto, new Producto());
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> resultado = productoService.listarProductos();

        // Assert
        assertEquals(2, resultado.size());
        verify(productoRepository).findAll();
    }

    @Test
    void guardarProducto_devuelveProductoGuardado_siDestinoValido() {
        // Arrange
        when(productoRepository.save(producto)).thenReturn(producto);

        // Act
        Producto resultado = productoService.guardarProducto(producto);

        // Assert
        assertNotNull(resultado);
        assertEquals("Paella valenciana", resultado.getNombre());
        verify(productoRepository).save(producto);
    }

    @Test
    void guardarProducto_lanzaExcepcion_siDestinoInvalido() {
        // Arrange
        producto.setDestino("TERRAZA");

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
            () -> productoService.guardarProducto(producto));
        verify(productoRepository, never()).save(any());
    }

    @Test
    void obtenerProductoPorId_devuelveProducto_siExiste() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        Producto resultado = productoService.obtenerProductoPorId(1L);

        // Assert
        assertEquals("Paella valenciana", resultado.getNombre());
    }

    @Test
    void obtenerProductoPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> productoService.obtenerProductoPorId(99L));
    }

    @Test
    void borrarProducto_invocaDeleteById_siSeLlama() {
        // Act
        productoService.borrarProducto(1L);

        // Assert
        verify(productoRepository).deleteById(1L);
    }
}
