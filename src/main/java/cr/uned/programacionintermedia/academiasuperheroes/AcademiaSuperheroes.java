package cr.uned.programacionintermedia.academiasuperheroes;

import cr.uned.programacionintermedia.academiasuperheroes.servicio.GestionSuperheroes;
import cr.uned.programacionintermedia.academiasuperheroes.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

/**
 * Clase principal de Academia de Superhéroes
 * 
 * Se encarga de inicial la aplicación gráfica
 * 
 * @author Nelson Andres Acuña Cambronero
 *
 * @version 1.0 
 */
public class AcademiaSuperheroes {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            
            //Creamos el administrador de superhéroes
            GestionSuperheroes gestionSuperheroes = new GestionSuperheroes();
            
            // Creamos un objeto de tipo Ventana Principal
            VentanaPrincipal ventana = new VentanaPrincipal(gestionSuperheroes);
            // Asignamos la ubicación en pantalla
            ventana.setLocationRelativeTo(null);
            // La hacemos visible al usuario
            ventana.setVisible(true);
        });
    }
}
