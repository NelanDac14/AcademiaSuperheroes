package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 * Clase padre Superheroe
 *
 * Representa un superhéroe dentro del sistema con diferentes
 * atributos en general que puede tener cualquier superhéroe 
 * sin importar su clase o tipo de superhéroe
 * 
 * @author Nelson Andrés Acuña Cambronero
 * 
 * @version 1.0
 */
public abstract class Superheroe {

    private static int contadorId;
    private String idHeroe;
    private String nombreHeroico;
    private String nombreReal;
    private String estado;
    private double salarioMensual;

    /**
     * Crea un Superhéroe dentro del sistema
     * 
     * @param idHeroe ID único del superhéroe
     *
     * @param nombreHeroico Nombre del Superhéroe: debe tener mínimo 3
     * caracteres y debe ser ingresado por usuario.
     *
     * @param nombreReal Nombre real del superhéroe: debe tener mínimo 3
     * caracteres y debe ser ingresado por usuario.
     *
     * @param estado Selección única del usuario entre las siguientes opciones
     * (Disponible, En misión o Recuperación)
     *
     * @param salarioMensual Salario mensual del Superhéroe, dato ingresado por
     * el usuario
     */
    public Superheroe(String idHeroe, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        this.idHeroe = idHeroe;
        this.nombreHeroico = nombreHeroico;
        this.nombreReal = nombreReal;
        this.estado = estado;
        this.salarioMensual = salarioMensual;
    }

    //Costructor sobrecargado
    public Superheroe() {
        this.idHeroe = "";
        this.nombreHeroico = "";
        this.nombreReal = "";
        this.estado = "";
        this.salarioMensual = 0.0;
    }

    // Getter and Setter
    
    // Obtiene el contador ID
    public static int getContadorId() {
        return contadorId;
    }

    // Coloca el contador ID
    public static void setContadorId(int contadorId) {
        Superheroe.contadorId = contadorId;
    }
        
    // Obtiene el ID del Héroe
    public String getIdHeroe() {
        return idHeroe;
    }

    // Coloca el ID del Héroe
    public void setIdHeroe(String idHeroe) {
        this.idHeroe = idHeroe;
    }

    // Obtiene el Nombre Heroico
    public String getNombreHeroico() {
        return nombreHeroico;
    }

    // Coloca el nombre heroico
    public void setNombreHeroico(String nombreHeroico) {
        this.nombreHeroico = nombreHeroico;
    }

    // Obtiene el nombre real
    public String getNombreReal() {
        return nombreReal;
    }

    //Coloca el nombre real
    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    // Obtiene el estado
    public String getEstado() {
        return estado;
    }

    // Coloca el estado
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Obtiene el salario mensual
    public double getSalarioMensual() {
        return salarioMensual;
    }

    // Coloca el salario mensual
    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }
    
    //Métodos adicionales
    
    /**
     * Genera el ID del superhéroe
     * 
     * Genera automáticamente el ID del héroe cada vez que sea
     * necesario, en casos de agregar nuevos superhéroes
     * 
     * Este debe asignarse mediante el setIdHeroe
     * 
     * @return Retorna el ID del superhéroe para asignarse al 
     * héroe
     */
    public String generadorIdHeroe() {
        // Aumentamos el contador de héroes
        contadorId++;
        return String.format("HER-%02d", contadorId);
    }    
    
    //Método abstracto
    public abstract double calcularBonificacion();
}
