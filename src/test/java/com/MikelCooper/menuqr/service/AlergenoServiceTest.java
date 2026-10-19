package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.Alergeno;
import com.MikelCooper.menuqr.repository.AlergenoRepository;
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
 * Pruebas unitarias del servicio {@link AlergenoService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia.</p>
 */
@ExtendWith(MockitoExtension.class)
class AlergenoServiceTest {

    @Mock
    private AlergenoRepository alergenoRepository;

    @InjectMocks
    private AlergenoService alergenoService;

    private Alergeno alergeno;

    @BeforeEach
    void inicializarAlergeno() {
        alergeno = new Alergeno();
        alergeno.setNombre("Gluten");
        alergeno.setLinkImagen("🌾");
    }

    @Test
    void listarAlergenos_devuelveListaCompleta_siExistenAlergenos() {
        // Arrange
        List<Alergeno> alergenos = Arrays.asList(alergeno, new Alergeno());
        when(alergenoRepository.findAll()).thenReturn(alergenos);

        // Act
        List<Alergeno> resultado = alergenoService.listarAlergenos();

        // Assert
        assertEquals(2, resultado.size());
        verify(alergenoRepository).findAll();
    }

    @Test
    void guardarAlergeno_devuelveAlergenoGuardado_siEsCorrecto() {
        // Arrange
        when(alergenoRepository.save(alergeno)).thenReturn(alergeno);

        // Act
        Alergeno resultado = alergenoService.guardarAlergeno(alergeno);

        // Assert
        assertNotNull(resultado);
        assertEquals("Gluten", resultado.getNombre());
        verify(alergenoRepository).save(alergeno);
    }

    @Test
    void obtenerAlergenoPorId_devuelveAlergeno_siExiste() {
        // Arrange
        when(alergenoRepository.findById(1L)).thenReturn(Optional.of(alergeno));

        // Act
        Alergeno resultado = alergenoService.obtenerAlergenoPorId(1L);

        // Assert
        assertEquals("Gluten", resultado.getNombre());
    }

    @Test
    void obtenerAlergenoPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(alergenoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> alergenoService.obtenerAlergenoPorId(99L));
    }

    @Test
    void obtenerAlergenoPorNombre_devuelveAlergeno_siExiste() {
        // Arrange
        when(alergenoRepository.findByNombre("Gluten")).thenReturn(alergeno);

        // Act
        Alergeno resultado = alergenoService.obtenerAlergenoPorNombre("Gluten");

        // Assert
        assertEquals("🌾", resultado.getLinkImagen());
    }

    @Test
    void actualizarAlergeno_devuelveAlergenoActualizado_siExiste() {
        // Arrange
        Alergeno nuevosDatos = new Alergeno();
        nuevosDatos.setNombre("Lactosa");
        nuevosDatos.setLinkImagen("🥛");
        when(alergenoRepository.findById(1L)).thenReturn(Optional.of(alergeno));
        when(alergenoRepository.save(alergeno)).thenReturn(alergeno);

        // Act
        Alergeno resultado = alergenoService.actualizarAlergeno(1L, nuevosDatos);

        // Assert
        assertEquals("Lactosa", resultado.getNombre());
        assertEquals("🥛", resultado.getLinkImagen());
    }

    @Test
    void borrarAlergeno_invocaDeleteById_siSeLlama() {
        // Act
        alergenoService.borrarAlergeno(1L);

        // Assert
        verify(alergenoRepository).deleteById(1L);
    }
}
