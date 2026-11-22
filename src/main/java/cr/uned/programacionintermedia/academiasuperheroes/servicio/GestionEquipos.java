package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.EquipoRescate;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los equipos de superhéroes
 * registrados en la academia
 *
 * @author Nelson Andrés Acuña Cambronero
 *
 * @version 1.0
 */
public class GestionEquipos {
    // Colección que almacena los equipos registrados
    private ArrayList<EquipoRescate> equipos;
    
    // Referencia al administrador de superhéroes
    private GestionSuperheroes gestionSuperheroes;
    
    /**
     * Constructor de la clase
     * 
     * @param gestionSuperheroes Administrador de superheroes
     */
    public GestionEquipos(GestionSuperheroes gestionSuperheroes) {
        this.equipos = new ArrayList<>();
        this.gestionSuperheroes = gestionSuperheroes;
    }
    
    
}
