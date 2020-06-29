/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

import ProblemaChocolatinas.DistribucionesAleatorias.AbstractComandInput;
import ProblemaChocolatinas.DistribucionesAleatorias.DistribucionAleatoriaInput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author German le yo
 */
 // <>
public class Sistema {
    private Agente[] agentes; // Agentes
    private final int communitySize; // Numero de agentes

    public final double p; // Probabilidad de amistad entre agentes
    
    private Ambiente ambiente;
    private Observatorio observatorio;
    
    public Sistema(int communitySize, double p){
        this.agentes = new Agente[communitySize];
        this.communitySize = communitySize;
        this.p = p;
    }
    
    public void Community_Generator(){
        for(int i = 0; i < communitySize; i++){
            agentes[i] = new Agente(i, ambiente, observatorio);
	}
        double random;
        for(int i = 0; i < communitySize; i++){
            for(int j = i + 1; j < communitySize; j++){
                random = Math.random();
                if(p > random){
                    agentes[i].hacerAmigos(agentes[j]);
                    agentes[j].hacerAmigos(agentes[i]);
                }
            }
        }
    }
    
    public Agente[] getAgentes() {
        return agentes;
    }
    
    public int totalLaminasAgentes(){
        int totalLaminasAgentes = 0;
        for(Agente agente: agentes ){
            for(int j = 0; j < ambiente.totalLaminas; j++){
               totalLaminasAgentes += agente.getLaminas()[j];
            }      
        }
        return totalLaminasAgentes;
    }
    
    /**
     * La cantidad de láminas totales si todos los agentes llenan el album
     * @return Total de láminas * Número de agentes
     */
    public int meta(){
        return this.communitySize * ambiente.totalLaminas;
    }
    
    public void runSystem(Tienda tienda){
        int totalLaminasAgentes = 0;       
        for(Agente agent: agentes){
            System.out.printf("%s tiene de amistad con %s\n", agent, agent.getAmigos());
        }     
             

        ArrayList<AccionInterface> accionesSistema = new ArrayList<AccionInterface >();
        while (totalLaminasAgentes < this.meta()){
               
            for(Agente agente: agentes){
                accionesSistema.addAll(agente.seleccionarAcciones());
                
            } 
            
            for(AccionInterface accion: accionesSistema) accion.ejecutarAccion();
            
            accionesSistema.clear();
            totalLaminasAgentes = this.totalLaminasAgentes();  
            
            observatorio.step();
        }   
    }   
    
    public void setAmbiente(Ambiente ambiente){
        this.ambiente = ambiente;
    }
    
    public void setObservatorio(Observatorio observatorio){
        this.observatorio = observatorio;
    }
}
