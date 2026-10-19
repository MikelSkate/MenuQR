package com.MikelCooper.menuqr.util;

import org.springframework.stereotype.Component;
import com.MikelCooper.menuqr.service.AlergenoService;
import com.MikelCooper.menuqr.model.Alergeno;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Inicializador que carga los alérgenos en la base de datos.
 *
 * <p>Genera el conjunto de alérgenos alimentarios al arrancar la aplicación,
 * cada uno con su nombre y su representación visual mediante un emoji.</p>
 *
 * @author Mikel Cooper
 * @version 1.0
 */
@Component
public class AlergenoInitializer {

    /** Nombres de los alérgenos que se cargarán en la base de datos. */
    ArrayList<String> alergenos = new ArrayList<String>(List.of(
        "Gluten",
        "Lactosa",
        "Huevo",
        "Frutos secos",
        "Cacahuete",
        "Soja",
        "Marisco",
        "Pescado",
        "Apio",
        "Mostaza"));

    /** Emojis que representan visualmente cada alérgeno, en el mismo orden. */
    ArrayList<String> linksImagenes = new ArrayList<String>(List.of(
        "🌾",   // Gluten
        "🥛",   // Lactosa
        "🥚",   // Huevo
        "🌰",   // Frutos secos
        "🥜",   // Cacahuete
        "🫘",   // Soja
        "🦐",   // Marisco
        "🐟",   // Pescado
        "🌿",   // Apio
        "🟡")); // Mostaza

    @Autowired
    private AlergenoService alergenoService;

    /**
     * Crea y guarda todos los alérgenos con su nombre y emoji correspondiente.
     */
    @Transactional
    public void inicializarAlergenos() {

        for (int i = 0; i < alergenos.size() ; i++) {
            Alergeno alergeno = new Alergeno();
            alergeno.setNombre(alergenos.get(i));
            alergeno.setLinkImagen(linksImagenes.get(i));
            alergenoService.guardarAlergeno(alergeno);
        }
    }
}