/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import ProblemaChocolatinas.Agente;
import ProblemaChocolatinas.DistribucionesAleatorias.*;
import java.util.Scanner;

/**
 *
 * @author German le yo
 */
public abstract class AbstractComandInput implements DistribucionAleatoriaInput{
    protected Scanner commandLine;
    
    public AbstractComandInput(Scanner commandLine){
        this.commandLine = commandLine;
    }
    
    public static class UniformeCommandInput extends AbstractComandInput{

        public UniformeCommandInput(Scanner commandLine) {
            super(commandLine);
        }

        @Override
        public DistribucionAleatoria getDistribucion(int albumSize) {
            return new Uniforme(1, albumSize);
        }

    }
    
    public static class GeometricaTruncadaCommandInput extends AbstractComandInput{

        public GeometricaTruncadaCommandInput(Scanner commandLine) {
            super(commandLine);
        }

        @Override
        public DistribucionAleatoria getDistribucion(int albumSize) {
            System.out.print("Ingrese la probabilidad de éxito de la distribución Geométrica: ");
            double p = commandLine.nextDouble();
            return new GeometricaTruncada(p, 1, albumSize);
        }

    }
    
    public static class BinomialCommandInput extends AbstractComandInput{

        public BinomialCommandInput(Scanner commandLine) {
            super(commandLine);
        }

        @Override
        public DistribucionAleatoria getDistribucion(int albumSize) {
            System.out.print("Ingrese la probabilidad de éxito de la distribución Binomial: ");
            double p = commandLine.nextDouble();

            return new Binomial(albumSize, p);
        }

    }
    
    public static class PoissonTruncadaCommandInput extends AbstractComandInput{

        public PoissonTruncadaCommandInput(Scanner commandLine) {
            super(commandLine);
        }

        @Override
        public DistribucionAleatoria getDistribucion(int albumSize) {
            System.out.print("Ingrese el parámetro λ de la distribución Poisson: ");
            int λ = commandLine.nextInt();
            return new PoissonTruncada(λ, 1, albumSize);
        }

    }
    
    public static class TableCommandInput extends AbstractComandInput{

        public TableCommandInput(Scanner commandLine) {
            super(commandLine);
        }

        @Override
        public DistribucionAleatoria getDistribucion(int albumSize) {
            
            String explanation = new StringBuilder()
            .append("Para ingresar una distribución en forma de tabla no es necesario que ")
            .append("la suma de todos los valores sea 1. El programa sumará todos los valores ")
            .append("que se le han pasado y con eso calculará las probabilidades individuales de cada elemento. ")
            .append("Por ejemplo: [0.5, 0.5, 1], cuya suma total es 2, dará a entender que la probabilidad del ")
            .append("elemento 1 es de 0.5/2 = 0.25, por ende el vector de probabilidades final quedaria ")
            .append("[0.25, 0.25, 0.5].").toString();
            
            System.out.println(explanation);
            
            double[] probs = new double[albumSize];
            
            for (int i = 0; i < albumSize; i++) {
                System.out.printf("Ingrese el valor del elemento %d: ", i + 1);
                probs[i] = commandLine.nextDouble();
            }
            return new Tabla(probs);
        }

    }
    
}
