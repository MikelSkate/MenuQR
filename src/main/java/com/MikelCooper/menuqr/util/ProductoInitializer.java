package com.MikelCooper.menuqr.util;

import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import com.MikelCooper.menuqr.model.Categoria;
import com.MikelCooper.menuqr.model.Producto;
import com.MikelCooper.menuqr.service.AlergenoService;
import com.MikelCooper.menuqr.service.CategoriaService;
import com.MikelCooper.menuqr.service.ProductoService;
import com.MikelCooper.menuqr.model.Alergeno;
import jakarta.transaction.Transactional;

/**
 * Inicializador que carga los productos del menú en la base de datos.
 *
 * <p>Cada producto se construye combinando varios arrays paralelos (nombre,
 * precio, imagen, descripción, destino, categoría y alérgenos), todos en el
 * mismo orden. Los productos se crean en orden aleatorio para que cada arranque
 * genere una disposición distinta.</p>
 *
 * <p>Requiere que las categorías y los alérgenos ya estén cargados en la base
 * de datos antes de ejecutarse.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class ProductoInitializer {

    /** Nombres de los productos disponibles. */
    ArrayList<String> nombres = new ArrayList<String>(List.of(
    // Postres
    "Tarta de queso", "Brownie con helado", "Crème brûlée", "Tiramisú", "Coulant de chocolate",
    // Entrantes
    "Croquetas de jamón", "Patatas bravas", "Ensaladilla rusa", "Gambas al ajillo", "Pan con tomate",
    // Principal
    "Paella valenciana", "Solomillo a la pimienta", "Merluza a la plancha", "Rabo de toro", "Secreto ibérico",
    // Cócteles
    "Mojito", "Margarita", "Piña colada", "Aperol spritz", "Negroni",
    // Cervezas
    "Estrella Damm", "Voll-Damm", "Heineken", "Moritz", "Alhambra Reserva",
    // Refrescos
    "Coca-Cola", "Fanta naranja", "Agua con gas", "Nestea", "Sprite",
    // Tapas
    "Pintxo de tortilla", "Boquerón en vinagre", "Champiñones al ajillo", "Morcilla con pimiento", "Pulpo a la gallega"));

    /** Precios de los productos, en el mismo orden que {@link #nombres}. */
    ArrayList<Float> precios = new ArrayList<Float>(List.of(
    // Postres
    5.50f, 6.00f, 5.00f, 5.50f, 6.50f,
    // Entrantes
    4.50f, 3.50f, 4.00f, 8.50f, 2.50f,
    // Principal
    14.00f, 18.50f, 15.00f, 16.50f, 17.00f,
    // Cócteles
    8.00f, 8.50f, 7.50f, 9.00f, 9.50f,
    // Cervezas
    2.50f, 3.00f, 2.50f, 2.50f, 3.50f,
    // Refrescos
    2.00f, 2.00f, 1.50f, 2.50f, 2.00f,
    // Tapas
    3.00f, 2.50f, 4.00f, 3.50f, 5.50f));

    /** Enlaces de las imágenes de los productos, en el mismo orden que {@link #nombres}. */
    ArrayList<String> linksImagenes = new ArrayList<String>(List.of(
    // Postres
    "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=400",
    "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=400",
    "https://images.unsplash.com/photo-1470124182917-cc6e71b22ecc?w=400",
    "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400",
    "https://images.unsplash.com/photo-1624353365286-3f8d62daad51?w=400",
    // Entrantes
    "https://images.unsplash.com/photo-1674480367370-4e4bd64eb3c5?q=80&w=1332&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1565599837634-134bc3aadce8?q=80&w=735&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://plus.unsplash.com/premium_photo-1663127718713-c90bab585483?q=80&w=1224&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1659951226926-a75791782250?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400",
    // Principal
    "https://images.unsplash.com/photo-1534080564583-6be75777b70a?w=400",
    "https://images.unsplash.com/photo-1558030006-450675393462?w=400",
    "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=400",
    "https://images.unsplash.com/photo-1574484284002-952d92456975?w=400",
    "https://images.unsplash.com/photo-1544025162-d76694265947?w=400",
    // Cócteles
    "https://images.unsplash.com/photo-1551538827-9c037cb4f32a?w=400",
    "https://images.unsplash.com/photo-1556855810-ac404aa91e85?w=400",
    "https://images.unsplash.com/photo-1607644536940-6c300b5784c5?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1560512823-829485b8bf24?w=400",
    "https://images.unsplash.com/photo-1551751299-1b51cab2694c?w=400",
    // Cervezas
    "https://images.unsplash.com/photo-1608270586620-248524c67de9?w=400",
    "https://images.unsplash.com/photo-1535958636474-b021ee887b13?w=400",
    "https://images.unsplash.com/photo-1618885472179-5e474019f2a9?w=400",
    "https://images.unsplash.com/photo-1571613316887-6f8d5cbf7ef7?w=400",
    "https://images.unsplash.com/photo-1598562925445-9532172510ca?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    // Refrescos
    "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400",
    "https://images.unsplash.com/photo-1624517452488-04869289c4ca?w=400",
    "https://images.unsplash.com/flagged/photo-1553353799-a992825b0d07?q=80&w=1073&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=400",
    "https://images.unsplash.com/photo-1625772299848-391b6a87d7b3?w=400",
    // Tapas
    "https://images.unsplash.com/photo-1765100020448-4daa6a9bd934?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3Dw=400",
    "https://images.unsplash.com/photo-1515443961218-a51367888e4b?w=400",
    "https://plus.unsplash.com/premium_photo-1726743654453-3be7034802c9?q=80&w=903&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400",
    "https://images.unsplash.com/photo-1594041680534-e8c8cdebd659?w=400",
    "https://images.unsplash.com/photo-1582629413153-f78e0c252cf5?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D?w=400"));

    /** Descripciones de los productos, en el mismo orden que {@link #nombres}. */
    ArrayList<String> descripcion = new ArrayList<String>(List.of(
    // Postres
    "Tarta de queso cremosa horneada al horno con base de galleta y mermelada de frutos rojos.",
    "Brownie de chocolate con nueces acompañado de una bola de helado de vainilla.",
    "Clásico postre francés con crema de vainilla y costra de azúcar caramelizado.",
    "Postre italiano de bizcocho empapado en café con mascarpone y cacao en polvo.",
    "Bizcocho de chocolate con interior fundente servido caliente con helado.",
    // Entrantes
    "Croquetas caseras de jamón ibérico con bechamel cremosa y rebozado crujiente.",
    "Patatas fritas con salsa brava picante y alioli casero.",
    "Ensaladilla tradicional con patata, zanahoria, huevo, atún y mayonesa casera.",
    "Gambas salteadas al ajillo con aceite de oliva virgen extra y guindilla.",
    "Pan de cristal tostado con tomate rallado, aceite de oliva y sal en escamas.",
    // Principal
    "Arroz con mariscos, pollo y verduras cocinado a fuego lento con azafrán.",
    "Solomillo de ternera a la pimienta verde con patatas asadas y verduras de temporada.",
    "Merluza fresca a la plancha con patatas panadera y pimientos asados.",
    "Rabo de toro estofado con vino tinto y verduras durante 6 horas de cocción lenta.",
    "Secreto ibérico a la brasa con chimichurri casero y patatas fritas.",
    // Cócteles
    "Ron blanco con menta fresca, lima, azúcar y soda. El clásico cubano.",
    "Tequila con triple seco y jugo de lima servido con borde de sal.",
    "Ron con zumo de piña y crema de coco servido bien frío.",
    "Aperol con prosecco, soda y naranja. El aperitivo italiano por excelencia.",
    "Ginebra, vermut rojo y Campari a partes iguales con piel de naranja.",
    // Cervezas
    "Cerveza rubia española de carácter suave y refrescante con toque amargo.",
    "Cerveza doble malta de sabor intenso y cuerpo robusto con notas tostadas.",
    "Cerveza rubia internacional de sabor equilibrado y refrescante.",
    "Cerveza de Barcelona elaborada con agua del Montseny y lúpulo selecto.",
    "Cerveza lager de alta fermentación con sabor intenso y persistente.",
    // Refrescos
    "Refresco de cola clásico servido muy frío con hielo y limón.",
    "Refresco de naranja con sabor afrutado y refrescante servido con hielo.",
    "Agua mineral con gas servida bien fría con rodaja de limón.",
    "Refresco de té frío con sabor a limón y melocotón servido con hielo.",
    "Refresco de lima-limón refrescante y suave servido con hielo.",
    // Tapas
    "Pintxo de tortilla de patata casera sobre pan tostado con alioli.",
    "Boquerones marinados en vinagre con ajo y perejil fresco.",
    "Champiñones salteados al ajillo con perejil y un toque de vino blanco.",
    "Morcilla de Burgos con pimiento rojo asado sobre pan tostado.",
    "Pulpo cocido a la gallega con pimentón, aceite de oliva y sal gruesa."));

    /** Destino de preparación de cada producto, en el mismo orden que {@link #nombres}. */
    ArrayList<String> destinos = new ArrayList<String>(List.of(
    // Postres
    "COCINA", "COCINA", "COCINA", "COCINA", "COCINA",
    // Entrantes
    "COCINA", "COCINA", "COCINA", "COCINA", "COCINA",
    // Principal
    "COCINA", "COCINA", "COCINA", "COCINA", "COCINA",
    // Cócteles
    "BAR", "BAR", "BAR", "BAR", "BAR",
    // Cervezas
    "BAR", "BAR", "BAR", "BAR", "BAR",
    // Refrescos
    "BAR", "BAR", "BAR", "BAR", "BAR",
    // Tapas
    "COCINA", "COCINA", "COCINA", "COCINA", "COCINA"));

    /** Categoría de cada producto, en el mismo orden que {@link #nombres}. */
    ArrayList<String> nombresCategorias = new ArrayList<>(List.of(
    "Postres", "Postres", "Postres", "Postres", "Postres",
    "Entrantes", "Entrantes", "Entrantes", "Entrantes", "Entrantes",
    "Principal", "Principal", "Principal", "Principal", "Principal",
    "Cócteles", "Cócteles", "Cócteles", "Cócteles", "Cócteles",
    "Cervezas", "Cervezas", "Cervezas", "Cervezas", "Cervezas",
    "Refrescos", "Refrescos", "Refrescos", "Refrescos", "Refrescos",
    "Tapas", "Tapas", "Tapas", "Tapas", "Tapas"));

    /** Lista de alérgenos de cada producto, en el mismo orden que {@link #nombres}. */
    ArrayList<List<String>> alergenosPorProducto = new ArrayList<>(List.of(
    // Postres
    List.of("Gluten", "Lactosa", "Huevo"),                  // Tarta de queso
    List.of("Gluten", "Lactosa", "Huevo", "Frutos secos"),  // Brownie con helado
    List.of("Lactosa", "Huevo"),                            // Crème brûlée
    List.of("Gluten", "Lactosa", "Huevo"),                  // Tiramisú
    List.of("Gluten", "Lactosa", "Huevo"),                  // Coulant de chocolate
    // Entrantes
    List.of("Gluten", "Lactosa", "Huevo"),                  // Croquetas de jamón
    List.of("Huevo"),                                       // Patatas bravas
    List.of("Huevo", "Pescado"),                            // Ensaladilla rusa
    List.of("Marisco"),                                     // Gambas al ajillo
    List.of("Gluten"),                                      // Pan con tomate
    // Principal
    List.of("Marisco", "Pescado"),                          // Paella valenciana
    List.of(),                                              // Solomillo a la pimienta
    List.of("Pescado"),                                     // Merluza a la plancha
    List.of("Apio"),                                        // Rabo de toro
    List.of(),                                              // Secreto ibérico
    // Cócteles
    List.of(),                                              // Mojito
    List.of(),                                              // Margarita
    List.of(),                                              // Piña colada
    List.of(),                                              // Aperol spritz
    List.of(),                                              // Negroni
    // Cervezas
    List.of("Gluten"),                                      // Estrella Damm
    List.of("Gluten"),                                      // Voll-Damm
    List.of("Gluten"),                                      // Heineken
    List.of("Gluten"),                                      // Moritz
    List.of("Gluten"),                                      // Alhambra Reserva
    // Refrescos
    List.of(),                                              // Coca-Cola
    List.of(),                                              // Fanta naranja
    List.of(),                                              // Agua con gas
    List.of(),                                              // Nestea
    List.of(),                                              // Sprite
    // Tapas
    List.of("Gluten", "Huevo"),                             // Pintxo de tortilla
    List.of("Pescado"),                                     // Boquerón en vinagre
    List.of(),                                              // Champiñones al ajillo
    List.of("Gluten"),                                      // Morcilla con pimiento
    List.of("Marisco")));                                   // Pulpo a la gallega

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AlergenoService alergenoService;

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Crea y guarda todos los productos del menú en orden aleatorio.
     *
     * <p>Para cada producto resuelve su categoría y sus alérgenos a partir del
     * nombre, consultando los servicios correspondientes, y los asocia antes de
     * persistirlo.</p>
     */
    @Transactional
    public void inicializarProductos() {

        // Cambia el orden de los productos sin cambiar sus atributos
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        // Crea los productos
        for (int i = 0; i < nombres.size() ; i++) {
            Producto producto = new Producto();
            producto.setNombre(nombres.get(indices.get(i)));
            producto.setPrecio(precios.get(indices.get(i)));
            producto.setLinkImagen(linksImagenes.get(indices.get(i)));
            producto.setDescripcion(descripcion.get(indices.get(i)));
            producto.setDestino(destinos.get(indices.get(i)));
            Categoria categoria = categoriaService.obtenerCategoriaPorNombre(nombresCategorias.get(indices.get(i)));
            producto.setCategoria(categoria);
            // Asigna los alergenos correspondientes a este producto
            List<String> nombresAlergenos = alergenosPorProducto.get(indices.get(i));
            List<Alergeno> alergenosProducto = new ArrayList<>();
            for (String nombreAlergeno : nombresAlergenos) {
                alergenosProducto.add(alergenoService.obtenerAlergenoPorNombre(nombreAlergeno));
            }
            producto.setAlergenos(alergenosProducto);
            productoService.guardarProducto(producto);
        }
    }
}