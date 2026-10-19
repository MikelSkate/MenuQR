
package com.MikelCooper.menuqr.util;
 
import org.springframework.stereotype.Component;
import com.MikelCooper.menuqr.service.MesaService;
import com.MikelCooper.menuqr.service.PedidoService;
import com.MikelCooper.menuqr.config.InitializationConfig;
import com.MikelCooper.menuqr.model.Mesa;
import com.MikelCooper.menuqr.model.Pedido;
 
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
 
/**
 * Inicializador que genera pedidos aleatorios en la base de datos.
 *
 * <p>Utiliza Java Faker para crear un número de pedidos configurable, cada uno
 * asociado a una mesa aleatoria y con una fecha y hora generada restando días,
 * horas y minutos aleatorios al momento actual.</p>
 *
 * <p>Requiere que las mesas ya estén cargadas en la base de datos antes de
 * ejecutarse.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class PedidoInitializer {
 
    @Autowired
    private InitializationConfig config;
 
    @Autowired
    private PedidoService pedidoService;
 
    @Autowired
    private MesaService mesaService;
 
    /**
     * Crea y guarda pedidos aleatorios según el número configurado.
     *
     * <p>Cada pedido recibe una mesa aleatoria y una fecha y hora pasada
     * generada de forma aleatoria dentro de los últimos 30 días.</p>
     */
    @Transactional
    public void inicializarPedidos() {
 
        Faker faker = new Faker();
 
        for (int i = 0; i < config.getNumeroDePedidos() ; i++) {
            // Elige una mesa aleatoria de las mesas creadas
            List<Mesa> mesas = mesaService.listarMesas();
            Mesa mesaAleatoria = mesas.get(faker.number().numberBetween(0, mesas.size()));
            // Crea pedido y se le asignan sus atributos
            Pedido pedido = new Pedido();
            // Obtenemos el tiempo ahora y le restamos dias, horas y minutos de manera aleatoria
            LocalDateTime fecha = LocalDateTime.now()
                .minusDays(faker.number().numberBetween(1, 30))
                .minusHours(faker.number().numberBetween(0, 23))
                .minusMinutes(faker.number().numberBetween(0, 59))
                .truncatedTo(ChronoUnit.SECONDS); // Limita el tiempo a segundos
 
            pedido.setFechaHora(fecha);
            pedido.setMesa(mesaAleatoria);
            pedidoService.guardarPedido(pedido);
        }
    }
}