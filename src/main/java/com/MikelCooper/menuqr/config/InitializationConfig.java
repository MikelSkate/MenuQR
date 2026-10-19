package com.MikelCooper.menuqr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de los parámetros utilizados al generar los datos iniciales.
 *
 * <p>Define cantidades aleatorias y máximos que controlan cuántas mesas,
 * pedidos y líneas de pedido se crean al arrancar la aplicación. Los valores
 * pueden sobrescribirse desde {@code application.properties} usando el prefijo
 * {@code menuqr.inicializacion}.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "menuqr.inicializacion")
public class InitializationConfig {

    /** Número de mesas a generar (valor aleatorio entre 5 y 14 por defecto). */
    private int numeroDeMesas = new java.util.Random().nextInt(10) + 5;

    /** Número de pedidos a generar (valor aleatorio entre 5 y 54 por defecto). */
    private int numeroDePedidos = new java.util.Random().nextInt(50) + 5;

    /** Número máximo de líneas que puede tener un pedido. */
    private int maximoDeLineasDePedido = 10;

    /** Cantidad máxima de unidades de un producto por línea de pedido. */
    private int maximaCantidadDeProductosPorLinea = 12;

    /**
     * Obtiene el número de mesas a generar.
     *
     * @return el número de mesas.
     */
    public int getNumeroDeMesas() {
        return numeroDeMesas;
    }

    /**
     * Establece el número de mesas a generar.
     *
     * @param numeroDeMesas el nuevo número de mesas.
     */
    public void setNumeroDeMesas(int numeroDeMesas) {
        this.numeroDeMesas = numeroDeMesas;
    }

    /**
     * Obtiene el número de pedidos a generar.
     *
     * @return el número de pedidos.
     */
    public int getNumeroDePedidos() {
        return numeroDePedidos;
    }

    /**
     * Establece el número de pedidos a generar.
     *
     * @param numeroDePedidos el nuevo número de pedidos.
     */
    public void setNumeroDePedidos(int numeroDePedidos) {
        this.numeroDePedidos = numeroDePedidos;
    }

    /**
     * Obtiene el número máximo de líneas por pedido.
     *
     * @return el máximo de líneas de pedido.
     */
    public int getMaximoDeLineasDePedido() {
        return maximoDeLineasDePedido;
    }

    /**
     * Establece el número máximo de líneas por pedido.
     *
     * @param maximoDeLineasDePedido el nuevo máximo de líneas de pedido.
     */
    public void setMaximoDeLineasDePedido(int maximoDeLineasDePedido) {
        this.maximoDeLineasDePedido = maximoDeLineasDePedido;
    }

    /**
     * Obtiene la cantidad máxima de productos por línea de pedido.
     *
     * @return la cantidad máxima de productos por línea.
     */
    public int getMaximaCantidadDeProductosPorLinea() {
        return maximaCantidadDeProductosPorLinea;
    }

    /**
     * Establece la cantidad máxima de productos por línea de pedido.
     *
     * @param maximaCantidadDeProductosPorLinea la nueva cantidad máxima de productos por línea.
     */
    public void setMaximaCantidadDeProductosPorLinea(int maximaCantidadDeProductosPorLinea) {
        this.maximaCantidadDeProductosPorLinea = maximaCantidadDeProductosPorLinea;
    }
}
