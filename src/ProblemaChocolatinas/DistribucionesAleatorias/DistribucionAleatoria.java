/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import java.util.Random;

/**
 *
 * @author German le yo
 */
public interface DistribucionAleatoria {
    
    Random random = new Random();
    
    /**
     * Metodo que retornara elementos enteros en funcion de cada distribución 
     * aleatoria
     * @return Valor entero aleatorio correspondiente a una distribución de probabilidad
     */
    int getRandomInt();
}
