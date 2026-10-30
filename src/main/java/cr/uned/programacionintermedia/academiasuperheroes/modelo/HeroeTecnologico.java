package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 *
 * Clase hija: HeroeTecnologico
 * 
 * hereda de la clase padre: Superheroe
 * 
 * Representa un héroe tecnológico 
 * 
 * @author Nelson Andres Acuña Cambronero
 */
public class HeroeTecnologico extends Superheroe{
    
    private String nivelTecnologia;
    private int cantidadGadgets;

    //Constructores vacíos
    public HeroeTecnologico() {
        super("", "", "", 0.0);
        this.nivelTecnologia = "";
        this.cantidadGadgets = 0;
    }
    
    //Constructores sobrecargados
    public HeroeTecnologico(String nivelTecnologia, int cantidadGadgets, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        super(nombreHeroico, nombreReal, estado, salarioMensual);
        this.nivelTecnologia = nivelTecnologia;
        this.cantidadGadgets = cantidadGadgets;
    }

    public String getNivelTecnologia() {
        return nivelTecnologia;
    }

    public void setNivelTecnologia(String nivelTecnologia) {
        this.nivelTecnologia = nivelTecnologia;
    }

    public int getCantidadGadgets() {
        return cantidadGadgets;
    }

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
        switch (getEstado()) {
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
                throw new AssertionError();
        }
        
        //Retornamos al bonificación obtenida según el nivel y su cantidad de gadgets
        return boniNivelTec + (getCantidadGadgets() * 50);
    }
}
