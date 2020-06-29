/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author German le yo
 */
public class Observatorio {
    
    static class State{
        /**
         * Representa la cantidad de laminas totales del sistema, incluyendo las
         * que estan en todos los albumes de los agenes y las que tienen de sobra
         */
        int totalLaminasSistema;
        
        /**
         * Rpresenta la cantiadd de laminas faltantes de cada agente para que llene su album
         */
        int[] laminasFaltantesAgente;
        
        /**
         * Cantidd total de intercambios ocurridos en una iteración
         */
        int totalIntercambios;
        
        /**
         * Cantidad de sobrs vendidos en una iteración
         */
        int sobresVendidos;

        public State(int totalLaminasSistema, int[] laminasFaltantesAgente, int totalIntercambios, int sobresVendidos) {
            this.totalLaminasSistema = totalLaminasSistema;
            this.laminasFaltantesAgente = laminasFaltantesAgente;
            this.totalIntercambios = totalIntercambios;
            this.sobresVendidos = sobresVendidos;
        }
        
        
    }

    private Sistema sistema;
    
    ArrayList<State> estadoTemporal;

    private int steps;
    private int[] totalAmigosAgente;
    
    private int intercambiosStep;
    private int compraSobresStep;
    
    public Observatorio(){
        steps = 0;
        estadoTemporal = new ArrayList();
        intercambiosStep = 0;
        compraSobresStep = 0;
    }
    
    public void setSistema(Sistema sistema){
        this.sistema = sistema;
    }
    
    public void initEstadisticas(){
        Agente[] agentes = sistema.getAgentes();
        totalAmigosAgente = new int[agentes.length];
        for(int i = 0; i < agentes.length; i++){
            totalAmigosAgente[i] = agentes[i].getAmigos().size();
        }
    }
    
    public void step(){
        steps++;
        Agente[] agentes = sistema.getAgentes();
        
        int totalLaminasSistema = 0;
        int[] laminasFaltantesAgente = new int[agentes.length];
        for(int i = 0; i < agentes.length; i++){
            for (int lamina: agentes[i].getLaminas()) {
                totalLaminasSistema += lamina;
                if(lamina == 0) laminasFaltantesAgente[i]++;
            }
            
            for(int lamina: agentes[i].getLaminas_sobrantes()) totalLaminasSistema += lamina;
        }
        
        
        int sobresVendidos = compraSobresStep;
        compraSobresStep = 0;
        
        int totalIntercambios = intercambiosStep;
        intercambiosStep= 0;
        
        State state = new State(totalLaminasSistema, laminasFaltantesAgente, totalIntercambios, sobresVendidos);
        estadoTemporal.add(state);
    }
    
    
    public void addIntercambios(int inercambios){
        this.intercambiosStep += inercambios;
    }
    
    public void addCompraSobres(int sobres){
        this.compraSobresStep += sobres;
    }
    
    public void printEstadisticas(){
        System.out.printf("Total de pasos necesarios: %d\n", steps);
        System.out.printf("Numero de amigos por agente: %s\n", Arrays.toString(totalAmigosAgente));
        
        int totalIntercambios = 0;
        int totalSobresVendidos = 0;
        
        int index = 0;
        for(State state :estadoTemporal){
            System.out.printf("En el tiempo %d se dio que:\n", index);
            System.out.printf("\tTotal de láminas del sistema: %d\n", state.totalLaminasSistema);
            System.out.printf("\tLas láminas faltantes por cada agente: %s\n", Arrays.toString(state.laminasFaltantesAgente));
            System.out.printf("\tTotal intercambios: %d\n", state.totalIntercambios);
            System.out.printf("\tSobres Vendidos: %d\n", state.sobresVendidos);
            
            totalIntercambios += state.totalIntercambios;
            totalSobresVendidos += state.sobresVendidos;
            
            index++;
        }
        
        System.out.printf("Total intercambios: %d\nTotal sonbres vendidos: %d\n", totalIntercambios, totalSobresVendidos);
        
    }
}
