package com.MikelCooper.menuqr.util;

import org.springframework.stereotype.Component;
import com.MikelCooper.menuqr.service.CategoriaService;
import com.MikelCooper.menuqr.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Inicializador que carga las categorías de productos en la base de datos.
 *
 * <p>Genera el conjunto fijo de categorías del menú en orden aleatorio al
 * arrancar la aplicación.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class CategoriaInitializer {

    /** Nombres de las categorías que se cargarán en la base de datos. */
    ArrayList<String> categorias = new ArrayList<String>(List.of(
        "Postres",
        "Entrantes",
        "Principal",
        "Cócteles",
        "Cervezas",
        "Refrescos",
        "Tapas"));

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Crea y guarda todas las categorías del menú en orden aleatorio.
     */
    @Transactional
    public void inicializarCategorias() {

        Collections.shuffle(categorias);

        for (int i = 0; i < categorias.size() ; i++) {
            Categoria categoria = new Categoria();
            categoria.setNombre(categorias.get(i));
            categoriaService.guardarCategoria(categoria);
        }
    }
}