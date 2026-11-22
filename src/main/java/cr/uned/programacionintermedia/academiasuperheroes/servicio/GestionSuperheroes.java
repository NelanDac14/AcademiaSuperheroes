package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los superhéroes registrados en la academia
 *
 * @author Nelson Andrés Acuña Cambronero
 *
 * @version 1.0
 */
public class GestionSuperheroes {

    // Collecciones independientes según el tipo de héroe
    private ArrayList<HeroeTecnologico> heroesTecnologicos;
    private ArrayList<HeroeMutante> heroesMutantes;
    private ArrayList<HeroeMagico> heroesMagicos;

    /**
     * Constructor de la clase. Inicializa las colecciones de superhéroes
     *
     * @param heroesTecnologicos Colección de héroes tecnológicos
     * @param heroesMutantes Colección de héroes Mutantes
     * @param heroesMagicos Colección de héroes Mágicos
     */
    public GestionSuperheroes(ArrayList<HeroeTecnologico> heroesTecnologicos, ArrayList<HeroeMutante> heroesMutantes, ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesTecnologicos = heroesTecnologicos;
        this.heroesMutantes = heroesMutantes;
        this.heroesMagicos = heroesMagicos;
    }
    // Constructor sobrecargado

    public GestionSuperheroes() {
        this.heroesTecnologicos = new ArrayList<>();
        this.heroesMutantes = new ArrayList<>();
        this.heroesMagicos = new ArrayList<>();
    }

    /**
     * Registra un nuevo superhéroe tecnológico
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     *
     * @param nombreHeroico Nombre del héroe (Alias)
     *
     * @param nombreReal Nombre real del superhéroe
     *
     * @param estado Estado actual del superhéroe
     *
     * @param salarioMensual Salario mensual del superhéroes
     *
     * @param nivelTecnologia Nivel tecnológico del superhéroe
     *
     * @param cantidadGadgets Cantidad de gadgets que utiliza
     *
     * @return True si el registro fue exitoso; False si los datos no cumplen
     * las validaciones
     */
    public boolean agregarHeroeTecnologico(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String nivelTecnologia,
            int cantidadGadgets) {
        // Validamos los datos generales del superhéroe
        if (!validarDatosGenerales(nombreHeroico, nombreReal, estado,
                salarioMensual)) {
            return false;
        }

        // Validamos los datos específicos del héroe tecnológico
        if (!esOpcionValida(nivelTecnologia, "Básico", "Intermedio", "Avanzado")
                || cantidadGadgets < 0) {
            return false;
        }

        // Creamos el objeto con los datos proporcionados
        HeroeTecnologico nuevoHeroe = new HeroeTecnologico(
                nivelTecnologia.trim(),
                cantidadGadgets,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        //Generamos y asignamos el identificador único
        nuevoHeroe.setIdHeroe(nuevoHeroe.generadorIdHeroe());

        // Agregamos el objeto a la colección correspondiente
        heroesTecnologicos.add(nuevoHeroe);

        //Retornamos True
        return true;
    }

    /**
     * Registra un nuevo superhéroe mutante.
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     *
     * @param nombreHeroico Nombre heroico.
     *
     * @param nombreReal Nombre real.
     *
     * @param estado Estado actual.
     *
     * @param salarioMensual Salario mensual.
     *
     * @param tipoMutacion Tipo de mutación.
     *
     * @param controlPoder Nivel de control del poder.
     *
     * @return true si se registró correctamente; False si los datos no cumplen
     * las validaciones
     */
    
    public boolean agregarHeroeMutante(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String tipoMutacion,
            int controlPoder) {

        if (!validarDatosGenerales(nombreHeroico, nombreReal,
                estado, salarioMensual)) {
            return false;
        }

        if (!esOpcionValida(tipoMutacion,
                "Genética", "Radioactiva", "Experimental")
                || controlPoder < 1 || controlPoder > 100) {
            return false;
        }

        HeroeMutante nuevo = new HeroeMutante(
                tipoMutacion.trim(),
                controlPoder,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        nuevo.setIdHeroe(nuevo.generadorIdHeroe());

        heroesMutantes.add(nuevo);

        return true;
    }

    /**
     * Registra un nuevo superhéroe mágico.
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     * 
     * @param nombreHeroico Nombre heroico.
     * 
     * @param nombreReal Nombre real.
     * 
     * @param estado Estado actual.
     * 
     * @param salarioMensual Salario mensual.
     * 
     * @param tipoMagia Tipo de magia.
     * 
     * @param cantidadHechizos Cantidad de hechizos.
     * 
     * @return true si se registró correctamente; False si
     * los datos no cumplen las validaciones
     */
    public boolean agregarHeroeMagico(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String tipoMagia,
            int cantidadHechizos) {

        if (!validarDatosGenerales(nombreHeroico, nombreReal,
                estado, salarioMensual)) {
            return false;
        }

        if (!esOpcionValida(tipoMagia,
                "Elemental", "Oscura", "Sagrada")
                || cantidadHechizos < 0) {
            return false;
        }

        HeroeMagico nuevo = new HeroeMagico(
                tipoMagia.trim(),
                cantidadHechizos,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        nuevo.setIdHeroe(nuevo.generadorIdHeroe());

        heroesMagicos.add(nuevo);

        return true;
    }
    
    /**
     * Valida datos generales de superhéroe
     * 
     * Comprueba que los datos generales del superhéroe
     * cumpla con los requisitos establecidos
     * 
     * @return true si los datos son validos, false si estos
     * son incorrectos
     */
    private boolean validarDatosGenerales(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual){
        
        return nombreHeroico != null
                && nombreHeroico.trim().length() >= 3
                && nombreReal != null
                && nombreReal.trim().length() >= 3
                && esOpcionValida(
                        estado, "Disponible", "En misión", "Recuperación")
                && Double.isFinite(salarioMensual)
                && salarioMensual >= 0;
        
    }
    
    /**
     * Valida si la opción es valida o no
     * 
     * Comprueba si un valor pertenece a las opciones
     * permitidas para un determinado atributo
     * 
     * @param valor Valor que se desea comprobar
     * 
     * @param opciones Valores permitidos
     * 
     * @return true si coincido con alguna opción; 
     * false caso contrario.
     */
    
    private boolean esOpcionValida(String valor, String... opciones){
        // Validamos que el valor no sea nulo
        if(valor == null){
            return false;
        }
        
        // Recorremos las opciones para comparar el valor seleccionado
        for(String opcion:opciones){
            if(opcion.equals(valor.trim())){
                return true;                
            }
        }
        
        return false;
    }
}
