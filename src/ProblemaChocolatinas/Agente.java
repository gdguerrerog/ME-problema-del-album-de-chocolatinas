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
public class Agente {
    public static final int TOTAL_LAMINAS = 5;
    private int[] laminas = new int[TOTAL_LAMINAS];
    private int[] laminas_sobrantes = new int[TOTAL_LAMINAS];
    private ArrayList<Integer> amistad = new ArrayList<Integer>();
    public int identificador = 0;
    // Laminas int[]
    // Total Laminas int
    // friends agent[]
    // select action()
    // proposeChange(agent)
    // <>
    public void inicializarLaminas() {
        for (int i = 0; i < TOTAL_LAMINAS; i++){
            this.laminas[i] = 0;
        }
        for (int i = 0; i < TOTAL_LAMINAS; i++){
            this.laminas_sobrantes[i] = 0;
        }
    }
    public Agente(int indentificador){
        this.identificador = indentificador;
        this.inicializarLaminas();
    }
    public ArrayList<Acciones> seleccionarAcciones(int indiceSobre, Sistema sistema){
        Acciones accion;
        ArrayList<Acciones> acciones = new ArrayList<Acciones>();
        boolean laminasEncontradas = this.VerificarCambiosAmigos(sistema);
        int laminas_recolectadas = 0;
        for (int i = 0; i < TOTAL_LAMINAS; i++){
            laminas_recolectadas += laminas[i];
        }
        if (laminas_recolectadas == 0 || this.contarAmigos() == 0 || laminasEncontradas == false){
            accion = new Acciones(indiceSobre,1,indiceSobre,1);
            acciones.add(accion);
        }else{
            acciones = CambiosAmigos(sistema,indiceSobre);
        }
        return acciones;
    }
    public ArrayList<Integer> buscarAmigos(){
        return this.amistad;
    }
    public void hacerAmigos(int relacion){
        this.amistad.add(relacion);
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
        for (int i = 0; i < this.amistad.size(); i++){
            System.out.println(" "+ this.amistad.get(i)+" "+ Arrays.toString(sistema.getAgentes().get(this.amistad.get(i)).getLaminas_sobrantes()) +"");
        }
    }
    public int[] inicializarPedidos(){
        int[] pedidos = new int[TOTAL_LAMINAS];
        for (int i = 0; i < TOTAL_LAMINAS; i++){
            pedidos[i] = 0;
        }
        return pedidos;
    }
    
    public ArrayList<Acciones> CambiosAmigos(Sistema sistema,int indice){
        Acciones accion;
        ArrayList<Acciones> acciones = new ArrayList<Acciones>();
        int[] estadoActualLaminas = this.getLaminas();
        int[] pedidos = this.inicializarPedidos();
        int[] contador = this.inicializarPedidos();
        for (int i = 0; i < this.amistad.size(); i++){
            for (int j = 0; j < TOTAL_LAMINAS; j++){
                if (estadoActualLaminas[j] == 0 && contador[j] == 0 && sistema.getAgentes().get(this.amistad.get(i)).getLaminas_sobrantes()[j] != 0){
                    pedidos[j] = 1;  
                    contador[j] = 1;
                }
            }
            accion = new Acciones(pedidos,this.amistad.get(i),indice,0);
            acciones.add(accion);
            pedidos = this.inicializarPedidos();
        }              
        return acciones;
    }
    public boolean VerificarCambiosAmigos(Sistema sistema){
        int laminasEncontradas = 0;
        int[] estadoActualLaminas = this.getLaminas();
        int[] pedidos = this.inicializarPedidos();
        int[] contador = this.inicializarPedidos();
        for (int i = 0; i < this.amistad.size(); i++){
            for (int j = 0; j < TOTAL_LAMINAS; j++){
                if (estadoActualLaminas[j] == 0 && contador[j] == 0 && sistema.getAgentes().get(this.amistad.get(i)).getLaminas_sobrantes()[j] != 0){       
                    contador[j] = 1;
                }
            }
        }         
        for (int i = 0; i < TOTAL_LAMINAS; i++){
            laminasEncontradas += contador[i];     
        }
        if (laminasEncontradas == 0){
            return false;
        }else{
            return true;
        }        
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
}
