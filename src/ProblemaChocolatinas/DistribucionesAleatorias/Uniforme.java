/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

/**
 *
 * @author German le yo
 */
public class Uniforme implements DistribucionAleatoria{

    private final int minValue, maxValue;
    
    /**
     * Constructor de una distribución uniforme
     * @param minValue Valor mínimo a retornar de la distribución uniforme (Inclusivo)
     * @param maxValue Valor máximo a retornar de la distribución uniforme (Inclusivo)
     */
    public Uniforme(int minValue, int maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    @Override
    public int getRandomInt() {
        return minValue + random.nextInt(maxValue - minValue + 1);
    }
    
}
