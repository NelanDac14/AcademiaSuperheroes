package cr.uned.programacionintermedia.academiasuperheroes.modelo;

/**
 * Clase EquipoRescate
 *
 * Representa un equipo de rescate dentro 
 * del sistema, dispone de una serie de atributos
 * y contiene una lista de super héroes que 
 * componen dicho equipo.
 * 
 * @author Nelson Andrés Acuña Cambronero
 * 
 * @version 1.0
 */

import java.util.ArrayList;

public class EquipoRescate {    

//Atributos de la clase
    // Contador interno de ID
    private static int contadorId;
    // ID del equipo
    private String idEquipo;
    // Nombre del equipo
    private String nombreEquipo;
    // Tipo de emergencia
    private String tipoEmergencia;
    // Nivel de amenaza 
    private String nivelAmenaza;
    // Ciudad a la que se asigna el equipo
    private String ciudadAsignada;
    // Uso de tecnología
    private String usoTecnologiaEspecial;
    // Lista de superhéroes 
    private ArrayList<Superheroe> listaSuperheroes;
    // Costo operativo del equipo
    private double costoOperativo;
    
    /**
     * Crea un equipo de rescate dentro del sistema
     * 
     * @param idEquipo Identificacíon única del equipo
     * 
     * @param nombreEquipo Nombre que recibirá el equipo de superhéroes, debe
     * tener al menos 3 caracteres
     * 
     * @param tipoEmergencia Tipo de emergencia para el cual el equipo es 
     * creado, dato que elige el usuario de las siguientes opciones: Desastres
     * naturales, Invasión alienígena, Amenaza nuclear o Villano de alto riesgo
     * 
     * @param nivelAmenaza Nivel de amenaza con el que se enfrentará el 
     * equipo
     * 
     * @param ciudadAsignada Ciudad a cargo del equipo
     * 
     * @param usoTecnologiaEspecial Declaración de uso de tecnología especial
     * 
     * @param listaSuperheroes Superhéroes que forman parte de este equipo de 
     * rescate
     * 
     * @param costoOperativo Valor que conlleva desplegar a este equipo de 
     * rescate
     */
    public EquipoRescate(String idEquipo, String nombreEquipo, String tipoEmergencia, String nivelAmenaza, String ciudadAsignada, String usoTecnologiaEspecial, ArrayList<Superheroe> listaSuperheroes, double costoOperativo) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.tipoEmergencia = tipoEmergencia;
        this.nivelAmenaza = nivelAmenaza;
        this.ciudadAsignada = ciudadAsignada;
        this.usoTecnologiaEspecial = usoTecnologiaEspecial;
        this.listaSuperheroes = listaSuperheroes;
        this.costoOperativo = costoOperativo;
    }
    
    // Constructor sobrecargado
    public EquipoRescate() {
        this.idEquipo = "";
        this.nombreEquipo = "";
        this.tipoEmergencia = "";
        this.nivelAmenaza = "";
        this.ciudadAsignada = "";
        this.usoTecnologiaEspecial = "";
        this.listaSuperheroes = new ArrayList<>();
        this.costoOperativo = 0.0;
    }
    
    // Getter and Setter

    // Obtiene el contador de ID
    public static int getContadorId() {
        return contadorId;
    }

    // Coloca el contador del ID
    public static void setContadorId(int contadorId) {
        EquipoRescate.contadorId = contadorId;
    }

    // Obtiene el ID del equipo
    public String getIdEquipo() {
        return idEquipo;
    }

    // Coloca el ID del equipo
    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    // Obtiene el Nombrre del equipo
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    // Coloca el nombre del equipo
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    // Obtiene el tipo de emergencia
    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    // Coloca el tipo de emergencia
    public void setTipoEmergencia(String tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
    }

    // Obtiene el nivel de amenaza
    public String getNivelAmenaza() {
        return nivelAmenaza;
    }

    // Coloca el nivel de amenaza
    public void setNivelAmenaza(String nivelAmenaza) {
        this.nivelAmenaza = nivelAmenaza;
    }

    // Obtiene la ciudad a la que fue asignada
    public String getCiudadAsignada() {
        return ciudadAsignada;
    }

    // Coloca la ciudad asignada del equipo
    public void setCiudadAsignada(String ciudadAsignada) {
        this.ciudadAsignada = ciudadAsignada;
    }

    // Obtiene si este usa tecnología especial o no
    public String getUsoTecnologiaEspecial() {
        return usoTecnologiaEspecial;
    }

    // Coloca si este usa tecnología especia o no
    public void setUsoTecnologiaEspecial(String usoTecnologiaEspecial) {
        this.usoTecnologiaEspecial = usoTecnologiaEspecial;
    }

    // Obtiene la lista de superhéroes que conforma el equipo
    public ArrayList<Superheroe> getListaSuperheroes() {
        return listaSuperheroes;
    }

    // Coloca la lista de superhéroes que compone el equipo de resacate
    public void setListaSuperheroes(ArrayList<Superheroe> listaSuperheroes) {
        this.listaSuperheroes = listaSuperheroes;
    }

    // Obtiene el costo operativo que vale este equipo
    public double getCostoOperativo() {
        return costoOperativo;
    }

    // Coloca el costo operativo que vale este equipo
    public void setCostoOperativo(double costoOperativo) {
        this.costoOperativo = costoOperativo;
    }
    

    // Métodos adicionales
    
    /**
     * Genera el ID del equipo
     * 
     * Genera automáticamente el ID del equipo cada vez que sea
     * necesario, en casos de agregar nuevos equipos de rescate
     * 
     * Este debe asignarse mediante el setIdEquipo
     * 
     * @return Retorna el ID del equipo de rescate para asignarse al 
     * equipo
     */
    public String generadorIdEquipo() {
        // Aumentamos el contador de héroes
        contadorId++;
        return String.format("EQU-%02d", contadorId);
    }
    
    /**
     * Calcula el costo operativo del equipo de rescate
     * 
     * En base a la lista de superhéroes asignada, toma los 
     * salarios mensuales de estos, añadiendo valor adicional según 
     * nivel de amenaza, si este utiliza tecnología y en caso de
     * que el grupo poseea más de 5 superhéroes.
     * 
     * También se toma en cuenta las bonificaciones correspondiente a cada
     * héroe del grupo y se suman al costo operativo
     */
    public void calcularCostoOperativo(){
        // Se reinicializa el costo para no acumular resultados
        costoOperativo = 0.0;
        
        // Suma de los salarios de todos los superhéroes pertenecientes al equipo
        // También se suman todas las bonificaciones de cada uno de ellos
        for(Superheroe superHeroe : listaSuperheroes){
            costoOperativo += superHeroe.getSalarioMensual();
            costoOperativo += superHeroe.calcularBonificacion();
        }
        
        //Costo adicional según nivel de amenaza
        switch (nivelAmenaza.toLowerCase()) {
            case "bajo": // Sumar $5,000.00 al costo operativo
                costoOperativo += 5000;
                break;
            case "medio": // Sumar $15,000.00 al costo operativo
                costoOperativo += 15000;                
                break;
            case "alto": // Sumar $30,000.00 al costo operativo
                costoOperativo += 30000;                
                break;
            default:
                // No aplicamos ningún valor adicional al costo operativo
                break;
        }
        
        // Costo adicional en caso de utilizar tecnología especial
        if(usoTecnologiaEspecial.equalsIgnoreCase("sí")){
            // Sumar $10,000.00 al costo operativo
            costoOperativo += 10000;
        }
        
        // Costo adicional por más de 5 superhéroes en el equipo
        if(listaSuperheroes.size() > 5){
            // Sumar $8,000.00  al costo operativo
            costoOperativo += 8000;
        }
    }
    
}
