/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

/**
 *
 * @author German le yo
 */
public class AcccionComprarSobre implements AccionInterface{

    private final Agente agente;
    private final Tienda tienda;
    
    AcccionComprarSobre(Agente agente, Tienda tienda){
        this.agente = agente;
        this.tienda = tienda;
    }
    
    @Override
    public void ejecutarAccion() {
        int[] sobre = tienda.getSobre();
        int lamina;
        for(int k = 0; k < sobre.length; k++){
            lamina = agente.getLaminas()[sobre[k]-1];
            if (lamina == 0){
                agente.setLaminas(sobre[k]-1,1);
            }
            if (lamina == 1){
                agente.setLaminas_sobrantes(sobre[k]-1,1);
            }
        } 
    }
    
}
