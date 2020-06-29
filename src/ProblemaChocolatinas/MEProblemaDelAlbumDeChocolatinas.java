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
        System.out.print("Ingrese el número de láminas: ");
        int albumSize =  10;//scan.nextInt();
        System.out.print("Ingrese el tamaño de la comunidad: ");
        int communitySize = 10;//scan.nextInt();
        System.out.print("Ingrese la probabilidad de amistad de la cominudad: ");
        double p = 0.5;//scan.nextDouble();
        
        
        DistribucionAleatoriaInput input = null;
        
        // Repetir hasta que se ingrese una opción valida
        do{
            System.out.println("Seleccione la distribución de probabilidad a usar: ");
            System.out.println("1: Uniforme");
            System.out.println("2: Binomial");
            System.out.println("3: Geométrica Truncada");
            System.out.println("4: Poisson Truncada");
            System.out.println("5: Tabla");

            int selection = scan.nextInt();

            switch(selection){
                case 1: input = new UniformeCommandInput(scan); break;
                case 2: input = new BinomialCommandInput(scan); break;
                case 3: input = new GeometricaTruncadaCommandInput(scan); break;
                case 4: input = new PoissonTruncadaCommandInput(scan); break;
                case 5: input = new TableCommandInput(scan); break;
            }
        }while(input == null);
        
        Observatorio observatorio = new Observatorio();
        
        Tienda tienda = new Tienda(input.getDistribucion(albumSize), observatorio);
        Ambiente ambiente = new Ambiente(tienda, albumSize);

        Sistema sistema = new Sistema(communitySize, p);
        observatorio.setSistema(sistema);
        sistema.setObservatorio(observatorio);
        sistema.setAmbiente(ambiente);
        sistema.Community_Generator();

        observatorio.setAmbiente(ambiente);
        observatorio.initEstadisticas();
        
        sistema.runSystem(tienda);
        
        observatorio.printEstadisticas();
        
    }
    
}
