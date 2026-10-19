package com.MikelCooper.menuqr.service;

import com.MikelCooper.menuqr.model.Mesa;
import com.MikelCooper.menuqr.repository.MesaRepository;
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
 * Pruebas unitarias del servicio {@link MesaService}.
 *
 * <p>Se utiliza Mockito para simular el repositorio y aislar la lógica
 * del servicio de la capa de persistencia.</p>
 */
@ExtendWith(MockitoExtension.class)
class MesaServiceTest {

    @Mock
    private MesaRepository mesaRepository;

    @InjectMocks
    private MesaService mesaService;

    private Mesa mesa;

    @BeforeEach
    void inicializarMesa() {
        mesa = new Mesa();
        mesa.setNumero(5);
        mesa.setMesaQr("qr-mesa-5");
    }

    @Test
    void listarMesas_devuelveListaCompleta_siExistenMesas() {
        // Arrange
        List<Mesa> mesas = Arrays.asList(mesa, new Mesa());
        when(mesaRepository.findAll()).thenReturn(mesas);

        // Act
        List<Mesa> resultado = mesaService.listarMesas();

        // Assert
        assertEquals(2, resultado.size());
        verify(mesaRepository).findAll();
    }

    @Test
    void guardarMesa_devuelveMesaGuardada_siEsCorrecta() {
        // Arrange
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        // Act
        Mesa resultado = mesaService.guardarMesa(mesa);

        // Assert
        assertNotNull(resultado);
        assertEquals(5, resultado.getNumero());
        verify(mesaRepository).save(mesa);
    }

    @Test
    void obtenerMesaPorId_devuelveMesa_siExiste() {
        // Arrange
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));

        // Act
        Mesa resultado = mesaService.obtenerMesaPorId(1L);

        // Assert
        assertEquals(5, resultado.getNumero());
    }

    @Test
    void obtenerMesaPorId_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(mesaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> mesaService.obtenerMesaPorId(99L));
    }

    @Test
    void obtenerMesaPorNumero_devuelveMesa_siExiste() {
        // Arrange
        when(mesaRepository.findByNumero(5)).thenReturn(Optional.of(mesa));

        // Act
        Mesa resultado = mesaService.obtenerMesaPorNumero(5);

        // Assert
        assertEquals("qr-mesa-5", resultado.getMesaQr());
    }

    @Test
    void obtenerMesaPorNumero_lanzaExcepcion_siNoExiste() {
        // Arrange
        when(mesaRepository.findByNumero(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
            () -> mesaService.obtenerMesaPorNumero(99));
    }

    @Test
    void borrarMesa_invocaDeleteById_siSeLlama() {
        // Act
        mesaService.borrarMesa(1L);

        // Assert
        verify(mesaRepository).deleteById(1L);
    }
}
