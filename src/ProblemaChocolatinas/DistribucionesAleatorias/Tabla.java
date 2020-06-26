/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import java.util.Arrays;

/**
 *
 * @author German le yo
 */
public class Tabla implements DistribucionAleatoria {

    
    private final double[] probs;
    
    /**
     * Contructor de una distribución aleatoria definida por tabla.
     * Define las probabilidades de cada elemento segun si índice.
     * probs[i] representa la probabilidad podendara de el elemento i + 1
     * Los valores totales de probs no necesariamente deben sumar 1, 
     * internamente se ponderan los valores a la suma total del arreglo
     * es decir
     * this.probs[i] = probs[i]/suma(probs[i])
     * @param probs 
     */
    public Tabla(double[] probs){
        
        double suma = 0;
        for(double prob: probs) suma += prob;
        
        this.probs = new double[probs.length];
        for(int i = 0; i < probs.length; i++) this.probs[i] = probs[i]/suma;
    }
    
    /**
     * Calcula in valor aleatorio basado en probs.
     * @return Un valor aleaotrio con probabilidad definidas en probs 
     */
    @Override
    public int getRandomInt() {
        double u = random.nextDouble();
        double distribution = 0;
        for (int i = 0; i < probs.length; i++) {
            distribution += probs[i];
            if(distribution > u) return i + 1;
        }
        
        return probs.length;
    }
    
    
}
