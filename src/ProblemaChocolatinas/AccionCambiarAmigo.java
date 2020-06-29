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
public class AccionCambiarAmigo implements AccionInterface{

    private final Agente solicitante, solicitado;
    private final int[] pedir;
    private Observatorio observatorio;
    
    /**
     * 
     * @param solicitante Agente que solicita las láminas
     * @param solicitado Agente al que se le solicitan las láminas, debe ser amigo del solicitante
     * @param pedir Laminas a pedir
     */
    public AccionCambiarAmigo(Agente solicitante, Agente solicitado, int[] pedir, Observatorio observatorio){
        this.solicitante = solicitante;
        this.solicitado = solicitado;
        this.pedir = pedir;
        this.observatorio = observatorio;
        
    }
    
    @Override
    public void ejecutarAccion() {
         // System.out.print(Arrays.toString(accion.pedir));     
        int[] existenciasSolicitado = solicitado.getLaminas_sobrantes();
        int[] laminasSolicitante = solicitante.getLaminas();
        
        int totalIntercambios = 0;
        
        for(int k = 0; k < existenciasSolicitado.length; k++){
            if (existenciasSolicitado[k] >= pedir[k]){
                existenciasSolicitado[k] -= pedir[k]; // Reducir las existencias del solicitado
                laminasSolicitante[k] += pedir[k]; // Aumentar las existencias del solicitante
                totalIntercambios += pedir[k];
            }
        }
        
        observatorio.addIntercambios(totalIntercambios);
    }
}
