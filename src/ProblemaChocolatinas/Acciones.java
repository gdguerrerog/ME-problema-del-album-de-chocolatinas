/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

/**
 *
 * @author elmar
 */
public class Acciones {
    public int comprar;
    public int[] pedir;
    public int tienda;
    public int identificadorAgente;
    public int indice;
    public int tipoAccion;
    /**
     * 
     * @param comprar
     * @param tienda 
     * Comprar me indica el indice del arreglo para elegir el sobre que se adquiere
     * tienda es solo para saber si se le pedirá a la tienda(producto de otra idea)
     */
    public Acciones(int comprar, int tienda,int indice, int tipoAccion ){
        this.comprar = comprar;
        this.tienda = tienda;
        this.indice = indice;
        this.tipoAccion = tipoAccion;
    }
    public Acciones(int[] pedir, int identificadorAgente,int indice, int tipoAccion){
        this.pedir = pedir;
        this.identificadorAgente = identificadorAgente;
        this.indice = indice;
        this.tipoAccion = tipoAccion;
        
    }


    public int getComprar() {
        return comprar;
    }

    public void setComprar(int comprar) {
        this.comprar = comprar;
    }

    public void setIdentificadorAgente(int identificadorAgente) {
        this.identificadorAgente = identificadorAgente;
    }


    public int getIdentificadorAgente() {
        return identificadorAgente;
    }
    
}
