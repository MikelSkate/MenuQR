package com.MikelCooper.menuqr.util;
 
import org.springframework.stereotype.Component;
import com.MikelCooper.menuqr.service.MesaService;
import com.MikelCooper.menuqr.config.InitializationConfig;
import com.MikelCooper.menuqr.model.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
 
/**
 * Inicializador que carga las mesas en la base de datos.
 *
 * <p>Genera un número de mesas determinado por {@link InitializationConfig},
 * asignando a cada una un número correlativo y su código QR correspondiente.</p>
 *
 * <p><strong>Nota:</strong> este archivo se ha reconstruido a partir del patrón
 * del resto de inicializadores. Revisa que coincida con tu implementación real
 * (especialmente cómo generas el {@code mesaQr}).</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class MesaInitializer {
 
    @Autowired
    private InitializationConfig config;
 
    @Autowired
    private MesaService mesaService;
 
    /**
     * Crea y guarda las mesas según el número configurado, asignando a cada una
     * su número correlativo y su código QR.
     */
    @Transactional
    public void inicializarMesas() {
 
        for (int i = 1; i <= config.getNumeroDeMesas() ; i++) {
            Mesa mesa = new Mesa();
            mesa.setNumero(i);
            mesa.setMesaQr("qr-mesa-" + i);
            mesaService.guardarMesa(mesa);
        }
    }
}
