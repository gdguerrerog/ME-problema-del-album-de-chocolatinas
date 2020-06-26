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
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        DistribucionAleatoriaInput input = new TableCommandInput(scan);
        Tienda tienda = new Tienda(input.getDistribucion());
        
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(tienda.getSobre()));
        }
        
        
    }
    
}
