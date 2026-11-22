package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 *
 * Clase hija: HeroeMagico
 * 
 * hereda de la clase padre: Superheroe
 * 
 * Representa un héroe mágico que mantiene
 * ciertos atributos específicos, para este caso son:
 * tipo de magia y cantidad de hechizos
 * 
 * @author Nelson Andres Acuña Cambronero
 * 
 * @version 1.0
 */
public class HeroeMagico extends Superheroe{
    private String tipoMagia; // Elemental, Oscura y Sagrada.
    private int cantHechizos; // Número entero ingresado por usuario

    /** 
     * Constructor de la clase
     * 
     * Representa un héroe mágico dentro del programa que
     * contiene un tipo de magia y una cantidad de hechizos 
     * como conocimiento para aplicar o atacar
     * 
     * @param tipoMagia Tipo de magia que usa
     * 
     * @param cantHechizos Cantidad de hechizos conocidos
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
     * 
     */
    public HeroeMagico(String tipoMagia, int cantHechizos, String idHeroe, String nombreHeroico, String nombreReal, String estado, double salarioMensual) {
        super(idHeroe, nombreHeroico, nombreReal, estado, salarioMensual);
        this.tipoMagia = tipoMagia;
        this.cantHechizos = cantHechizos;
    }
    
    // Constructor sobrecargado
    public HeroeMagico() {
        super("", "", "", "", 0.0);
        this.tipoMagia = "";
        this.cantHechizos = 0;
    }

    // Obtiene el tipo de magia
    public String getTipoMagia() {
        return tipoMagia;
    }

    // Coloca el tipo de magia
    public void setTipoMagia(String tipoMagia) {
        this.tipoMagia = tipoMagia;
    }

    // Obtiene la cantidad de hechizos
    public int getCantHechizos() {
        return cantHechizos;
    }

    // Coloca la cantidad de hechizos
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
