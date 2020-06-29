/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author German le yo
 */
public class Agente {
    
    private int[] laminas;
    private int[] laminas_sobrantes;
    private ArrayList<Agente> amistad;
    
    private final Ambiente ambiente;
    private final Observatorio observatorio;
    
    public final int identificador;
    // Laminas int[]
    // Total Laminas int
    // friends agent[]
    // select action()
    // proposeChange(agent)
    // <>
    public Agente(int identificador, Ambiente ambiente, Observatorio observatorio){
        amistad = new ArrayList<Agente>();
        
        this.identificador = identificador;
        this.ambiente = ambiente;
        this.observatorio = observatorio;
        
        laminas = new int[ambiente.totalLaminas];
        laminas_sobrantes = new int[ambiente.totalLaminas];
    }
    public ArrayList<AccionInterface> seleccionarAcciones(){
        AccionInterface accion;
        ArrayList<AccionInterface> acciones = new ArrayList<AccionInterface>();
        boolean laminasEncontradas = this.VerificarCambiosAmigos();
        int laminas_recolectadas = 0;
        for (int i = 0; i < ambiente.totalLaminas; i++){
            laminas_recolectadas += laminas[i];
        }
        if (laminas_recolectadas == 0 || this.contarAmigos() == 0 || laminasEncontradas == false){
            accion = new AcccionComprarSobre(this, ambiente.tienda);
            acciones.add(accion);
        }else{
            acciones = CambiosAmigos();
        }
        return acciones;
    }
    public ArrayList<Agente> getAmigos(){
        return this.amistad;
    }
    public void hacerAmigos(Agente amigo){
        this.amistad.add(amigo);
    }
    public int contarAmigos(){
        int totalAmigos = 0;
        for (int i = 0; i < this.amistad.size(); i++){
            totalAmigos++;
        }
        return totalAmigos;
    }
    public int[] obtenerEstadoActual(){
        return this.laminas;
    }
    public void obtenerEstadoAmigos(Sistema sistema){
        for (Agente amigo: amistad){
            System.out.printf("%d: %s\n", amigo.identificador, Arrays.toString(amigo.laminas_sobrantes));
        }
    }
    
    public ArrayList<AccionInterface> CambiosAmigos(){
        AccionInterface accion;
        ArrayList<AccionInterface> acciones = new ArrayList<AccionInterface>();
        int[] estadoActualLaminas = this.getLaminas();
        int[] pedidos = new int[ambiente.totalLaminas]; // Las láminas que se piden a cada amigo
        int[] contador = new int[ambiente.totalLaminas]; // Cuantas veces he pedido la lámia j
        for (Agente amigo: amistad){
            for (int j = 0; j < ambiente.totalLaminas; j++){
                if (estadoActualLaminas[j] == 0 && contador[j] == 0 && amigo.getLaminas_sobrantes()[j] != 0){
                    pedidos[j] = 1;  
                    contador[j] = 1;
                }
            }
            accion = new AccionCambiarAmigo(this, amigo, pedidos, observatorio);
            acciones.add(accion);
            pedidos = new int[ambiente.totalLaminas];
        }              
        return acciones;
    }
    public boolean VerificarCambiosAmigos(){
        int[] estadoActualLaminas = this.getLaminas();
        for (Agente amigo: amistad){
            for (int j = 0; j < ambiente.totalLaminas; j++){
                if (estadoActualLaminas[j] == 0 && amigo.getLaminas_sobrantes()[j] != 0){       
                    return true;
                }
            }
        }         
        return false;
    }
    public void setLaminas(int index, int laminas) {
        this.laminas[index] = laminas;
    }

    public void setLaminas_sobrantes(int index, int laminas_sobrantes) {
        this.laminas_sobrantes[index] += laminas_sobrantes;
    }

    public int[] getLaminas() {
        return laminas;
    }

    public int[] getLaminas_sobrantes() {
        return laminas_sobrantes;
    }
    
    @Override
    public String toString(){
        return "Agente " + this.identificador;
    }
}
