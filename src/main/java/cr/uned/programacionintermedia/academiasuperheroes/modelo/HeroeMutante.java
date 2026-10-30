package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 *
 * @author acuac
 */
public class HeroeMutante extends Superheroe{
    //Atributos
    private String tipoMutacion; // Genética, Radioactiva y Experimental.
    private int controlPoder; // Representa un número del 1 al 100

    // Método sobrecargado
    public HeroeMutante(String tipoMutacion, int controlPoder, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        super(nombreHeroico, nombreReal, estado, salarioMensual);
        this.tipoMutacion = tipoMutacion;
        this.controlPoder = controlPoder;
    }
    
    // Método vacío
    public HeroeMutante() {
        super("", "", "", 0.0);
        this.tipoMutacion = "";
        this.controlPoder = 0;
    }

    public String getTipoMutacion() {
        return tipoMutacion;
    }

    public void setTipoMutacion(String tipoMutacion) {
        this.tipoMutacion = tipoMutacion;
    }

    public int getControlPoder() {
        return controlPoder;
    }

    public void setControlPoder(int controlPoder) {
        this.controlPoder = controlPoder;
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
     *  monto por mutación + monto por nivel de control de poder
     */
    @Override
    public double calcularBonificacion() {
        //Varriables
        double boniTipoMuta = 0;
        double boniContrPoder = 0;
        
        //Validamos la mutación del héroe mutante
        switch (getTipoMutacion()) {
            case "Genética": //Bonificación de $50
                boniTipoMuta = 50;
                break;
            case "Radioactiva": //Bonificación de $500
                boniTipoMuta = 500;                
                break;
            case "Experimental": //Bonificación de $400
                boniTipoMuta = 400;                
                break;
            default:
                throw new AssertionError();
        }
        
        //Validamos el control de poder del héroe mutante
       if(getControlPoder() >= 0 || getControlPoder() <= 30){ // Entre 0-30
           boniContrPoder = 150;
       } else if(getControlPoder() > 30 || getControlPoder() <= 70){ // Entre 31-70
           boniContrPoder = 450;
       } else if (getControlPoder() > 70 || getControlPoder() <= 100){ // Entre 71-100
           boniContrPoder = 900;
       }
       
        //Retornamos al bonificación obtenida según el tipo de mutación y su control del poder
        return boniTipoMuta + boniContrPoder;
    }   
}
