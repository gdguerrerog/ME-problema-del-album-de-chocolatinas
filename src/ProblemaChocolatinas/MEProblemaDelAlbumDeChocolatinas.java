/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas;

import ProblemaChocolatinas.DistribucionesAleatorias.AbstractComandInput.*;
import ProblemaChocolatinas.DistribucionesAleatorias.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author German le yo
 */
public class MEProblemaDelAlbumDeChocolatinas {

    /**
     * @param args the command line arguments
     */
    //<>
    public static void main(String[] args) {  
        Scanner scan = new Scanner(System.in);
        DistribucionAleatoriaInput input = new AbstractComandInput.TableCommandInput(scan);
        Tienda tienda = new Tienda(input.getDistribucion());
        Sistema sistema = new Sistema();
        System.out.print("Ingrese el tamaño de la comunidad: ");
        int communitySize = scan.nextInt();
        
        Sistema agentesSistema = sistema.Community_Generator(communitySize, 0.3);
        agentesSistema.runSystem(tienda);
    }
    
}
