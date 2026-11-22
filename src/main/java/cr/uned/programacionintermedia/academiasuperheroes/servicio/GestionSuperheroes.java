package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.Superheroe;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author acuac
 */
public class GestionSuperheroes {
    
    private ArrayList<HeroeTecnologico> heroesTecnologicos;
    private ArrayList<HeroeMutante> heroesMutantes;
    private ArrayList<HeroeMagico> heroesMagicos;
    private int siguienteHeroe;

    // Constructor sobrecargado
    public GestionSuperheroes(ArrayList<HeroeTecnologico> heroesTecnologicos, ArrayList<HeroeMutante> heroesMutantes, ArrayList<HeroeMagico> heroesMagicos, int siguienteHeroe) {
        this.heroesTecnologicos = heroesTecnologicos;
        this.heroesMutantes = heroesMutantes;
        this.heroesMagicos = heroesMagicos;
        this.siguienteHeroe = siguienteHeroe;
    }
    
    
    // Constructor vacio
    public GestionSuperheroes() {
        this.heroesTecnologicos = new ArrayList<>();
        this.heroesMutantes = new ArrayList<>();
        this.heroesMagicos = new ArrayList<>();
        this.siguienteHeroe = 0;
    }
    
    //Getters & setters
    public ArrayList<HeroeTecnologico> getHeroesTecnologicos() {
        return heroesTecnologicos;
    }

    public void setHeroesTecnologicos(ArrayList<HeroeTecnologico> heroesTecnologicos) {
        this.heroesTecnologicos = heroesTecnologicos;
    }

    public ArrayList<HeroeMutante> getHeroesMutantes() {
        return heroesMutantes;
    }

    public void setHeroesMutantes(ArrayList<HeroeMutante> heroesMutantes) {
        this.heroesMutantes = heroesMutantes;
    }

    public ArrayList<HeroeMagico> getHeroesMagicos() {
        return heroesMagicos;
    }

    public void setHeroesMagicos(ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesMagicos = heroesMagicos;
    }
    
    //Métodos Adicionales
    
    /**
     * Formateo para expreción monetaria o contable
     *
     * Establece el formato en estilo decimal con dos decimales para referir un
     * dato que demastrará una expresión monetaria en dólares.
     *
     * @param monto recibe un tipo de dato int, con la intención de convertirlo
     * en formato monetario
     *
     * @return Retorna el dato con el formato monetario establecido
     */
    public static String formatoMonetario(double monto) {
        // Se establece los símbolos decimales a utilizar
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
        // Se establece la coma como seperador de grupos
        simbolos.setGroupingSeparator(',');
        // Se establece el punto con el separador de decimales
        simbolos.setDecimalSeparator('.');
        // Establece el formato decimal deseado al dato
        DecimalFormat formato_contable = new DecimalFormat("$#,##0.00", simbolos);
        // Retornamos el dato en string con el formato establecido
        return String.valueOf(formato_contable.format(monto));
    }
    
    /** 
     * Genera el siguiente código HER-XX.
     * 
     * Encargado de generar ID de superhéroes sin permitir la duplicación
     * del mismo, asegurando que este sea único para cada caso
     * 
     * @return retorna el valor de nuevo ID del superhéroe para asignarlo
     */
    public String generarIdHeroe() {
        return String.format(java.util.Locale.US, "HER-%02d", siguienteHeroe++);
    }
    
    /** 
     * Comprueba que el objeto recibido sea válido.
     * 
     * @param heroe Objeto de la clase Superheroe
     */
    private void validarHeroe(Superheroe heroe) {
        if (heroe == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de superhéroe.");
        }
    }
    
    /**Agrega un objeto a la colección correspondiente a su clase concreta.
     * 
     * Determina a que clase hija corresponde el héroe y lo agrega 
     * a su lista de héroes correspondiente
     * 
     * @param heroe Objeto de la clase Superheroe
     * 
     * En caso de no existir 
     * 
     * @throws IllegalArgumentException dato que no esta contemplado
     */
    private void agregarAListaPorTipo(Superheroe heroe) {
        if (heroe instanceof HeroeTecnologico) {
            heroesTecnologicos.add((HeroeTecnologico) heroe);
        } else if (heroe instanceof HeroeMutante) {
            heroesMutantes.add((HeroeMutante) heroe);
        } else if (heroe instanceof HeroeMagico) {
            heroesMagicos.add((HeroeMagico) heroe);
        } else {
            throw new IllegalArgumentException("El tipo de superhéroe no está contemplado.");
        }
    }

    /** Elimina un identificador de cualquiera de las tres colecciones. 
     * Encargado de eliminar un superhéroe de cualquiera de las listas
     * según su IdHeroe
     * 
     * @param idHeroe ID de superhéroe que se desea eliminar
     */
    private void quitarDeListaPorId(String idHeroe) {
        heroesTecnologicos.removeIf(heroe -> heroe.getIdHeroe().equalsIgnoreCase(idHeroe));
        heroesMutantes.removeIf(heroe -> heroe.getIdHeroe().equalsIgnoreCase(idHeroe));
        heroesMagicos.removeIf(heroe -> heroe.getIdHeroe().equalsIgnoreCase(idHeroe));
    }
    
    /**Actualizador de consecutivo ID Héroe.
     * 
     * Ajusta el consecutivo según el identificador más alto encontrado 
     * 
     * @param id Recibe el ID proveniente d
     */
    private void actualizarConsecutivoHeroe(String id) {
        try {
            //Substring desde la pos 4 "HER-'XX'" valida el número de ID
            int numero = Integer.parseInt(id.substring(4));
            //Aúmenta el último númera en 1 para dar el siguiente ID
            siguienteHeroe = Math.max(siguienteHeroe, numero + 1);
        } catch (RuntimeException error) {
            // Los identificadores ya se validan por lo que no es necesario
        }
    }
    
    
    
    /**
     * Agrega un Héroe Tecnológico que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes tecnológicos que corresponde a todos los de su tipo.
     * 
     * @param heroeTecnologico Objeto de tipo HeroeTecnológico
     * @return retorna el mismo héroe con su respectivo identificador
     */
    public HeroeTecnologico agregar(HeroeTecnologico heroeTecnologico){
        // Validamos que el héroe tecnológino no venga nulo
        if(heroeTecnologico == null){
            // Lanzamos un excepción notificando al usuario 
            throw new IllegalArgumentException("El héroe tecnológico recibido no puede ser nulo");
        }
        heroesTecnologicos.add(heroeTecnologico);
        
        //Retornamos el mismo héroe pero ya con todo y su identificador
        return heroeTecnologico;
    }
    
    /**
     * Agrega un Héroe Mutante que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes mutantes que corresponde a todos los de su tipo.
     * 
     * @param heroeMutante Objeto de tipo HeroeMutante
     */
    public void agregar(HeroeMutante heroeMutante){
        heroesMutantes.add(heroeMutante);
    }
    
    /**
     * Agrega un Héroe Mágico que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes mágicos que corresponde a todos los de su tipo.
     * 
     * @param heroeMagico Objeto de tipo HeroeMagico
     */
    public void agregar(HeroeMagico heroeMagico){
        heroesMagicos.add(heroeMagico);
    }
    
    public void actualizar(){
        
    }
    
    public void cambiarEstado(){
        
    }
    
    public void eliminar(){
        
    }
    
    public void guardar(){
        
    }

    public void cargar(){
        
    }   
    
    private void validarDatos(HeroeTecnologico heroeTecnologico){
        //Validamos que el nombre heroico no sea null y cumpla con el mínimo de 3 caracteres
        if(heroeTecnologico.getNombreHeroico() == null ||
                heroeTecnologico.getNombreHeroico().trim().length() < 3){
            throw new IllegalArgumentException("El nombre heroico debe tener al menos 3 caracteres");
        }
        
        //Validamos que el nombre real no sea null y cumpla con el mínimo de 3 caracteres
        if(heroeTecnologico.getNombreReal()== null ||
                heroeTecnologico.getNombreReal().trim().length() < 3){
            throw new IllegalArgumentException("El nombre real debe tener al menos 3 caracteres");
        }
        
        //Validamos que el nombre real no sea null y cumpla con el mínimo de 3 caracteres
        if(heroeTecnologico.getNombreReal()== null ||
                heroeTecnologico.getNombreReal().trim().length() < 3){
            throw new IllegalArgumentException("El nombre real debe tener al menos 3 caracteres");
        }
    }
}