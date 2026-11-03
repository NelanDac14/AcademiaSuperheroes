package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 *
 * @author acuac
 */
public class HeroeMagico extends Superheroe{
    private String tipoMagia; // Elemental, Oscura y Sagrada.
    private int cantHechizos; // Número entero ingresado por usuario

    // Constructor sobrecargado
    public HeroeMagico(String tipoMagia, int cantHechizos, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        super(nombreHeroico, nombreReal, estado, salarioMensual);
        this.tipoMagia = tipoMagia;
        this.cantHechizos = cantHechizos;
    }
    
    // Constructor vacío
    public HeroeMagico() {
        super("", "", "", 0.0);
        this.tipoMagia = "";
        this.cantHechizos = 0;
    }

    public String getTipoMagia() {
        return tipoMagia;
    }

    public void setTipoMagia(String tipoMagia) {
        this.tipoMagia = tipoMagia;
    }

    public int getCantHechizos() {
        return cantHechizos;
    }

    public void setCantHechizos(int cantHechizos) {
        this.cantHechizos = cantHechizos;
    }
    
    //

    /**
     * Calculo de bonificación
     * 
     * Método que se hereda de Superhéro y se sobre escribe,
     * su función principal es calcular la bonificación del
     * superhéroe mágico, esto según su ripo de magia y su cantidad
     * de hechizos
     * 
     * @return Devuelve la bonificación calculada según la fórmula:
     * 
     * bonificación por tipo de magia + bonificación por cantidad de hechizos
     */
    @Override
    public double calcularBonificacion(){
        double boniTipoMagia = 0;
        switch (getTipoMagia()) {
            case "Elemental": // Bonificación de $150
                boniTipoMagia = 150;
                break;
            case "Oscura": // Bonificación de 500
                boniTipoMagia = 500;
                break;
            case "Sagrada": // Bonificación de $600
                boniTipoMagia = 600;
                break;
            default:
                /* No aplicamos valor por default debido a que en la clase
                no se espera ningún valor incorrecto y por lo cual la validaciones
                no corresponden a esta clase*/
                break;
        }
        
        //Retornamos la bonificaciónpor tipo de magia + bonificación por la cantidad de hechizon
        return boniTipoMagia + (getCantHechizos() * 35);
    } 
}
