package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 *
 * Clase hija: HeroeTecnologico
 * 
 * hereda de la clase padre: Superheroe
 * 
 * Representa un héroe tecnológico que mantiene
 * ciertos atributos específicos, para este caso son:
 * nivel de tecnología y cantidad de gadgets
 * 
 * @author Nelson Andres Acuña Cambronero
 * 
 * @version 1.0
 */
public class HeroeTecnologico extends Superheroe{
    
    private String nivelTecnologia;
    private int cantidadGadgets;

    /** 
     * Constructor de la clase
     * 
     * Representa un héroe tecnológico dentro del sistema,
     * este tiene un nivel de uso de tecnología y una cantidad
     * de gadgets que utiliza
     * 
     * @param nivelTecnologia Nivel de tecnología que posee
     * 
     * @param cantidadGadgets Cantidad de gadgets que utiliza 
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
    public HeroeTecnologico(String nivelTecnologia, int cantidadGadgets, String idHeroe, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        super(idHeroe, nombreHeroico, nombreReal, estado, salarioMensual);
        this.nivelTecnologia = nivelTecnologia;
        this.cantidadGadgets = cantidadGadgets;
    }
    
    //Constructor sobrecargados    
    public HeroeTecnologico() {
        super("", "", "", "", 0.0);
        this.nivelTecnologia = "";
        this.cantidadGadgets = 0;
    }

    // Obtiene el nivel de tecnología
    public String getNivelTecnologia() {
        return nivelTecnologia;
    }

    // Coloca el nivel de tecnología
    public void setNivelTecnologia(String nivelTecnologia) {
        this.nivelTecnologia = nivelTecnologia;
    }

    // Obtiene la cantidad de gadgets
    public int getCantidadGadgets() {
        return cantidadGadgets;
    }

    // Coloca la cantidad de gadgets
    public void setCantidadGadgets(int cantidadGadgets) {
        this.cantidadGadgets = cantidadGadgets;
    }

    /**
     * Calculo de bonificación
     * 
     * Método que se hereda de Superhéro y se sobre escribe,
     * su función principal es calcular la bonificación del
     * superhéroe tecnológico, esto según su nivel y cantidad
     * de gadgets que utiliza.
     * 
     * @return Devuelve la bonificación calculada según la fórmula: 
     *  monto por nivel + (Cantidad de gadgets * 50)
     */
    @Override
    public double calcularBonificacion() {
        //Varriables
        double boniNivelTec = 0;
        
        //Validamos el nivel del superhéro tecnológico
        switch (getNivelTecnologia()) {
            case "Básico": //Bonificación de $200
                boniNivelTec = 200;
                break;
            case "Intermedio": //Bonificación de $500
                boniNivelTec = 500;                
                break;
            case "Avanzado": //Bonificación de $900
                boniNivelTec = 900;                
                break;
            default:
                /* No aplicamos valor por default debido a que en la clase
                no se espera ningún valor incorrecto y por lo cual la validaciones
                no corresponden a esta clase*/
                break;
        }
        
        //Retornamos al bonificación obtenida según el nivel y su cantidad de gadgets
        return boniNivelTec + (getCantidadGadgets() * 50);
    }
}
