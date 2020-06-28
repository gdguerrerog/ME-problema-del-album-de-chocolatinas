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
private HashMap<Integer, Agente> agentes;
private int communitySize;

private int relation = 0;
private int notRelation = 0;
public int[][] agentsAdyacenceMatrix;

    public Sistema(){
        this.agentes = new HashMap<Integer, Agente>();
        this.communitySize = 0;
    }
    
    public Sistema Community_Generator(int n, double p){
        this.agentes = new HashMap<Integer, Agente>();
        this.communitySize = n;
        this.agentsAdyacenceMatrix = new int[communitySize][communitySize];
        for(int i=0; i<communitySize; i++){
            agentes.put(i, new Agente(i));
	}
        this.initializeAdyacenceMatrix();
        for(int i = 0; i < communitySize; i++){
            for(int j = i+1; j < communitySize; j++){
                double random = Math.random();
                if(p > random){
                    this.relation++;
                    agentsAdyacenceMatrix[i][j] = 1;
                    agentsAdyacenceMatrix[j][i] = 1;
                    agentes.get(i).hacerAmigos(j);
                    agentes.get(j).hacerAmigos(i);
                }
                else{
                    this.notRelation++;
                }
            }
        }
        return this;
    }
    
    public int[][] initializeAdyacenceMatrix(){
        for(int i = 0; i < communitySize; i++){
            for(int j = 0; j < communitySize; j++){
                    this.agentsAdyacenceMatrix[i][j] = 0;
            }
        }  
        return agentsAdyacenceMatrix;
    }
    public HashMap<Integer, Agente> getAgentes() {
        return agentes;
    }
    
    public int totalLaminasAgentes(){
        int TOTAL_LAMINAS = Agente.TOTAL_LAMINAS;
        int totalLaminasAgentes = 0;
        for(int i = 0; i < communitySize; i++){
            for(int j = 0; j < TOTAL_LAMINAS; j++){
               totalLaminasAgentes += this.getAgentes().get(i).getLaminas()[j];
               
            }      
        }
        return totalLaminasAgentes;
    }
    
    public int meta(){
        return this.communitySize * Agente.TOTAL_LAMINAS;
    }
    
    public void correrAcciones(ArrayList<ArrayList<Acciones> > accionesSistema, int[][] sobresDisponibles){
 
        int lamina;
        int[] sobre;
        Acciones accion;
        int[] existencias;
        int validate = 0;
        
        for(int i = 0; i < accionesSistema.size(); i++){
            for(int j = 0; j < accionesSistema.get(i).size(); j++){
                accion = accionesSistema.get(i).get(j);
                if (accion.tipoAccion == 1){
                    sobre = sobresDisponibles[accion.getComprar()];
                    for(int k = 0; k < sobre.length; k++){
                        lamina = this.getAgentes().get(accion.indice).getLaminas()[sobre[k]-1];
                        if (lamina == 0){
                            this.getAgentes().get(accion.indice).setLaminas(sobre[k]-1,1);
                        }
                        if (lamina == 1){
                            this.getAgentes().get(accion.indice).setLaminas_sobrantes(sobre[k]-1,1);
                        }
                    }    
                }
                else if(accion.tipoAccion == 0){                  
                    // System.out.print(Arrays.toString(accion.pedir));     
                    existencias = this.getAgentes().get(accion.indice).getLaminas_sobrantes();
                    for(int k = 0; k < existencias.length; k++){
                        existencias[k] = existencias[k]-accion.pedir[k];
                        if (existencias[k] < 0){
                            validate = existencias[k];
                            k = existencias.length;
                        }
                    }
                    if (validate == 0){
                        for(int k = 0; k < accion.pedir.length; k++){
                            this.getAgentes().get(accion.indice).setLaminas(k,1);
                            
                        }
                    }
                    // System.out.print(Arrays.toString(this.getAgentes().get(accion.indice).getLaminas_sobrantes()));
                }
            }
        }
    }
    
    public void runSystem(Tienda tienda){
        int totalLaminasAgentes = 0;       
        for(int i = 0; i < communitySize; i++){
            System.out.println(Arrays.toString(this.agentsAdyacenceMatrix[i]));
        }     
             

        ArrayList<ArrayList<Acciones> > accionesSistema = new ArrayList<ArrayList<Acciones> >();
        int[][] sobresDisponibles = new int[communitySize+1][5];
        int pasos = 0;
        while (totalLaminasAgentes < this.meta()){
            for (int i = 0; i < communitySize+1; i++) {
                sobresDisponibles[i] = tienda.getSobre();
                // System.out.println(Arrays.toString(tienda.getSobre()));
            }    
            
            System.out.println("Laminas llenas en el paso:" + pasos);   
            for(int i = 0; i < communitySize; i++){
                /*
                System.out.print("El agente: \t");
                System.out.print(this.getAgentes().get(i).identificador);
                System.out.print("Es amigo de: \t");
                this.getAgentes().get(i).obtenerEstadoAmigos(this);
                */
                accionesSistema.add(this.getAgentes().get(i).seleccionarAcciones(i, this));
                
                System.out.println("Laminas llenas del agente:" + i);               
                System.out.println(Arrays.toString(this.getAgentes().get(i).getLaminas()));
                /*
                System.out.println("Laminas sobrantes");
                System.out.println(Arrays.toString(this.getAgentes().get(i).getLaminas_sobrantes()));
                */
                // System.out.print("\n");
                
            } 
            
            this.correrAcciones(accionesSistema, sobresDisponibles);
            totalLaminasAgentes = this.totalLaminasAgentes();  
            pasos++;
        }   
        System.out.println("Pasos que tomó para que todos los agentes llenaran el album");
        System.out.println(pasos);
        System.out.println("Suma laminas agentes");
        System.out.println(totalLaminasAgentes);
        System.out.println("Meta");
        System.out.println(this.meta());
    }   
}
