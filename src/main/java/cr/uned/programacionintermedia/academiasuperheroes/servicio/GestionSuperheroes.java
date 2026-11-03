package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import java.util.ArrayList;

/**
 *
 * @author acuac
 */
public class GestionSuperheroes {
    
    private ArrayList<HeroeTecnologico> heroesTecnologicos;
    private ArrayList<HeroeMutante> heroesMutantes;
    private ArrayList<HeroeMagico> heroesMagicos;

    // Constructor sobrecargado
    public GestionSuperheroes(ArrayList<HeroeTecnologico> heroesTecnologicos, ArrayList<HeroeMutante> heroesMutantes, ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesTecnologicos = heroesTecnologicos;
        this.heroesMutantes = heroesMutantes;
        this.heroesMagicos = heroesMagicos;
    }
    
    // Constructor vacio
    public GestionSuperheroes() {
        this.heroesTecnologicos = new ArrayList<>();
        this.heroesMutantes = new ArrayList<>();
        this.heroesMagicos = new ArrayList<>();
    }

    public ArrayList<HeroeTecnologico> getHeroesTecnologicos() {
        return heroesTecnologicos;
    }

    public void setHeroesTecnologicos(ArrayList<HeroeTecnologico> heroesTecnologicos) {
        this.heroesTecnologicos = heroesTecnologicos;
    }

    public ArrayList<HeroeMutante> getHeroesMutantes() {
        return heroesMutantes;
    }

    public void setHeroesMutantes(ArrayList<HeroeMutante> heroesMutantes) {
        this.heroesMutantes = heroesMutantes;
    }

    public ArrayList<HeroeMagico> getHeroesMagicos() {
        return heroesMagicos;
    }

    public void setHeroesMagicos(ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesMagicos = heroesMagicos;
    }
    
    //Métodos Adicionales
    
    /**
     * Agrega un Héroe Tecnológico que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes tecnológicos que corresponde a todos los de su tipo.
     * 
     * @param heroeTecnologico Objeto de tipo HeroeTecnológico
     */
    public void agregar(HeroeTecnologico heroeTecnologico){
        heroesTecnologicos.add(heroeTecnologico);
    }
    
    /**
     * Agrega un Héroe Mutante que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes mutantes que corresponde a todos los de su tipo.
     * 
     * @param heroeMutante Objeto de tipo HeroeMutante
     */
    public void agregar(HeroeMutante heroeMutante){
        heroesMutantes.add(heroeMutante);
    }
    
    /**
     * Agrega un Héroe Mágico que extiende de la clase 
     * padre SuperHeroe, únicamente recibe datos que correspondan
     * a un héroe de este tipo, este es agregado a su ArrayList
     * héroes mágicos que corresponde a todos los de su tipo.
     * 
     * @param heroeMagico Objeto de tipo HeroeMagico
     */
    public void agregar(HeroeMagico heroeMagico){
        heroesMagicos.add(heroeMagico);
    }
    
    public void actualizar(){
        
    }
    
    public void cambiarEstado(){
        
    }
    
    public void eliminar(){
        
    }
    
    public void guardar(){
        
    }

    public void cargar(){
        
    }    
}