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

    //Método abstracto
    public abstract double calcularBonificacion();
}
