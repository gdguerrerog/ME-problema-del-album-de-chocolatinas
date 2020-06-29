/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

import ProblemaChocolatinas.DistribucionesAleatorias.DistribucionAleatoria;

/**
 *
 * @author German le yo
 */
public class Tienda {

    public static final int TAMANO_SOBRE = 5;
    
    private DistribucionAleatoria random;
    
    private Observatorio observatorio;
    
    public Tienda(DistribucionAleatoria random, Observatorio observatorio){
        this.random = random;
        this.observatorio = observatorio;
    }
    
    public int[] getSobre(){
        int[] sobre = new int[TAMANO_SOBRE];
        for (int i = 0; i < TAMANO_SOBRE; i++) {
            sobre[i] = random.getRandomInt();
        }
        
        observatorio.addCompraSobres(1);
        
        return sobre;
    }
    
}
