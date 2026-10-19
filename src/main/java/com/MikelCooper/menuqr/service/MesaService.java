package com.MikelCooper.menuqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.MikelCooper.menuqr.model.Mesa;
import com.MikelCooper.menuqr.repository.MesaRepository;

/**
 * Servicio que gestiona la lógica de negocio de las mesas del restaurante.
 *
 * <p>Proporciona las operaciones CRUD sobre las mesas, así como la búsqueda
 * por número de mesa utilizada al asociar pedidos.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    /**
     * Obtiene la lista completa de mesas almacenadas.
     *
     * @return una lista con todas las mesas.
     */
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    /**
     * Guarda una nueva mesa.
     *
     * @param mesa la mesa a guardar.
     * @return la mesa guardada con su identificador asignado.
     */
    public Mesa guardarMesa(Mesa mesa) {
        // Falta implementar validacion de error
        return mesaRepository.save(mesa);
    }

    /**
     * Busca una mesa por su identificador.
     *
     * @param id el identificador de la mesa.
     * @return la mesa encontrada.
     * @throws RuntimeException si no existe ninguna mesa con ese identificador.
     */
    public Mesa obtenerMesaPorId(Long id) {
        return mesaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrado")); // Caso si no encuentra al usuario
    }

    /**
     * Actualiza los datos de una mesa existente.
     *
     * @param id el identificador de la mesa a actualizar.
     * @param nuevaMesa objeto con los nuevos datos de la mesa.
     * @return la mesa actualizada.
     * @throws RuntimeException si no existe ninguna mesa con ese identificador.
     */
    public Mesa actualizarMesa(Long id, Mesa nuevaMesa) {
        // Busca el usuario original
        Mesa mesaExistente = obtenerMesaPorId(id);
        // Actualiza campos
        mesaExistente.setNumero(nuevaMesa.getNumero());
        mesaExistente.setMesaQr(nuevaMesa.getMesaQr());
        return mesaRepository.save(mesaExistente);
    }

    /**
     * Elimina una mesa por su identificador.
     *
     * @param id el identificador de la mesa a eliminar.
     */
    public void borrarMesa(Long id) {
        mesaRepository.deleteById(id);
    }    

    /**
     * Busca una mesa por su número.
     *
     * @param numero el número de la mesa.
     * @return la mesa encontrada.
     * @throws RuntimeException si no existe ninguna mesa con ese número.
     */
    public Mesa obtenerMesaPorNumero(int numero) {
        return mesaRepository.findByNumero(numero)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
    }
}
