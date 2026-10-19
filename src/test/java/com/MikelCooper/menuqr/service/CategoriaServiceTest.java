package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.Categoria;
import com.MikelCooper.menuqr.repository.CategoriaRepository;
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
 * Pruebas unitarias del servicio {@link CategoriaService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia.</p>
 */
@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    void inicializarCategoria() {
        categoria = new Categoria();
        categoria.setNombre("Postres");
    }

    @Test
    void listarCategorias_devuelveListaCompleta_siExistenCategorias() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria, new Categoria());
        when(categoriaRepository.findAll()).thenReturn(categorias);

        // Act
        List<Categoria> resultado = categoriaService.listarCategorias();

        // Assert
        assertEquals(2, resultado.size());
        verify(categoriaRepository).findAll();
    }

    @Test
    void guardarCategoria_devuelveCategoriaGuardada_siEsCorrecta() {
        // Arrange
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        // Act
        Categoria resultado = categoriaService.guardarCategoria(categoria);

        // Assert
        assertNotNull(resultado);
        assertEquals("Postres", resultado.getNombre());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void obtenerCategoriaPorId_devuelveCategoria_siExiste() {
        // Arrange
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        // Act
        Categoria resultado = categoriaService.obtenerCategoriaPorId(1L);

        // Assert
        assertEquals("Postres", resultado.getNombre());
    }

    @Test
    void obtenerCategoriaPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> categoriaService.obtenerCategoriaPorId(99L));
    }

    @Test
    void actualizarCategoria_devuelveCategoriaActualizada_siExiste() {
        // Arrange
        Categoria nuevosDatos = new Categoria();
        nuevosDatos.setNombre("Entrantes");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        // Act
        Categoria resultado = categoriaService.actualizarCategoria(1L, nuevosDatos);

        // Assert
        assertEquals("Entrantes", resultado.getNombre());
    }

    @Test
    void borrarCategoria_invocaDeleteById_siSeLlama() {
        // Act
        categoriaService.borrarCategoria(1L);

        // Assert
        verify(categoriaRepository).deleteById(1L);
    }
}
