package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los superhéroes
 * registrados en la academia
 * 
 * @author Nelson Andrés Acuña Cambronero
 * 
 * @version 1.0
 */
public class GestionSuperheroes {
    // Collecciones independientes según el tipo de héroe
    private ArrayList<HeroeTecnologico> heroesTecnologicos;
    private ArrayList<HeroeMutante> heroesMutantes;
    private ArrayList<HeroeMagico> heroesMagicos;
    
    
    /**
     * Constructor de la clase.
     * Inicializa las colecciones de superhéroes
     * 
     * @param heroesTecnologicos Colección de héroes tecnológicos
     * @param heroesMutantes Colección de héroes Mutantes
     * @param heroesMagicos Colección de héroes Mágicos
     */
    public GestionSuperheroes(ArrayList<HeroeTecnologico> heroesTecnologicos, ArrayList<HeroeMutante> heroesMutantes, ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesTecnologicos = heroesTecnologicos;
        this.heroesMutantes = heroesMutantes;
        this.heroesMagicos = heroesMagicos;
    }
    // Constructor sobrecargado
    
    public GestionSuperheroes() {
        this.heroesTecnologicos = new ArrayList<>();
        this.heroesMutantes = new ArrayList<>();
        this.heroesMagicos = new ArrayList<>();
    }
}