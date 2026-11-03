package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 * Clase padre Superheroe
 *
 * @author Nelson Andrés Acuña Cambronero
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
    public Superheroe(String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        this.nombreHeroico = nombreHeroico;
        this.nombreReal = nombreReal;
        this.estado = estado;
        this.salarioMensual = salarioMensual;
    }
    
    //Costructor sobrecargado
    public Superheroe() {
        this.nombreHeroico = "";
        this.nombreReal = "";
        this.estado = "";
        this.salarioMensual = 0.0;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public String getIdHeroe() {
        return idHeroe;
    }

    public String getNombreHeroico() {
        return nombreHeroico;
    }

    public void setNombreHeroico(String nombreHeroico) {
        this.nombreHeroico = nombreHeroico;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }
    
    //Métodos adicionales
    public void generadorIdHeroe() {
        // Aumentamos el contador de héroes
        contadorId++;

        if (String.valueOf(contadorId).length() >= 2) {
            // En caso de que la numeración del contadorId sea mayor a 9
            this.idHeroe = "HER-" + contadorId;
        } else {
            // En caso de que la numeración del contadorId sea menor a 9
            this.idHeroe = "HER-0" + contadorId;
        }
    }
    
    //Método abstracto
    public abstract double calcularBonificacion();
}
