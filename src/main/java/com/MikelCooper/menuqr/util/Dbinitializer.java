package com.MikelCooper.menuqr.util;
 
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
 
/**
 * Inicializador principal de la base de datos.
 *
 * <p>Implementa {@link CommandLineRunner} para ejecutarse automáticamente al
 * arrancar la aplicación. Coordina la carga de datos iniciales invocando a cada
 * inicializador en el orden correcto, respetando las dependencias entre
 * entidades (por ejemplo, las categorías y los alérgenos deben existir antes
 * que los productos).</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class Dbinitializer implements CommandLineRunner {
 
    @Autowired
    private CategoriaInitializer inicializadorCategoria;
 
    @Autowired
    private AlergenoInitializer inicializadorAlergeno;
 
    @Autowired
    private ProductoInitializer inicializadorProducto;
 
    @Autowired
    private MesaInitializer inicializadorMesa;
 
    @Autowired
    private PedidoInitializer inicializadorPedido;
 
    @Autowired
    private LineaPedidoInitializer inicializadorLineaPedido;
 
    /**
     * Punto de entrada ejecutado automáticamente al arrancar la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    @Override
    public void run(String... args) {
        inicializarBaseDatos();
    }
 
    /**
     * Ejecuta la carga de datos iniciales en el orden adecuado para respetar
     * las dependencias entre entidades.
     */
    private void inicializarBaseDatos() {
        System.out.println("Iniciando la carga de datos iniciales...");
        inicializadorCategoria.inicializarCategorias();
        inicializadorAlergeno.inicializarAlergenos();
        inicializadorProducto.inicializarProductos();
        inicializadorMesa.inicializarMesas();
        inicializadorPedido.inicializarPedidos();
        inicializadorLineaPedido.inicializarLineaPedidos();
        System.out.println("Carga de datos completada.");
    }
}