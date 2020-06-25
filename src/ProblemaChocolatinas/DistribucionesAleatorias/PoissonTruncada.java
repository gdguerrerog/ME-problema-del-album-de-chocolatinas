/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author German le yo
 */
public class PoissonTruncada implements DistribucionAleatoria{

    public final int λ, min, max;
    private final BigDecimal probabilidadIntervalo;

    private static BigDecimal E = null;

    /**
     * Constructor de una variable aleatorio Poisson Truncada.
     * @param λ Parámetro lambda de la distribución Poisson
     * @param min Valor minimo de truncamiento de la variable (inclusivo)
     * @param max Valor máximo de truncamiento de la variable (inclusivo)
     */
    public PoissonTruncada(int λ, int min, int max){
        
        if(E == null) E = BigDecimal.valueOf(Math.E);
        
        this.λ = λ;
        this.min = min;
        this.max = max;
        
        
        BigDecimal probabilidadIntervalo = BigDecimal.ZERO;
        for (int i = min; i <= max; i++) {
            probabilidadIntervalo = probabilidadIntervalo.add(densidadNoTruncada(i));
        }
        
        this.probabilidadIntervalo = probabilidadIntervalo;
        System.out.println(probabilidadIntervalo);

    }
    
    
    /**
     * Función de densidad de una distribución Poisson no truncada.
     * calcula e^-λ*λ^x / x!
     * @param x Realización a calcular la probabilidad
     * @return Probabilidad de que la realización x ocurra en una destribución Poisson
     */
    public final BigDecimal densidadNoTruncada(int x){
        BigDecimal ans = BigDecimal.valueOf(λ).pow(x);
        ans = ans.multiply(E.pow(-1* λ, MathContext.DECIMAL128));
        for(int i = 2; i <= x; i++) ans = ans.divide(BigDecimal.valueOf(i), MathContext.DECIMAL128);
        return ans;
    }
    
    
    /**
     * Función de densidad de una distribución Poisson no truncada.
     * Calcula Poisson_λ(x)/P(x E [min, max])
     * Es decir Poisson de x dividido entre la probabilidad de que x
     * pertenezca al intervalo [min, max]
     * @param x Realización a calcular la probabilidad
     * @return Probabilidad de que la realización x ocurra en una destribución Poisson truncada
     */
    public final BigDecimal densidad(int x){
        if(x < min || x > max) return BigDecimal.ZERO;
        BigDecimal densidadNoTruncada = densidadNoTruncada(x);
        return densidadNoTruncada.divide(probabilidadIntervalo, MathContext.DECIMAL128);
    }
    
    @Override
    public int getRandomInt() {
        double u = random.nextDouble();
        BigDecimal uVal = BigDecimal.valueOf(u);
        BigDecimal acumulative = BigDecimal.ZERO;
        
        for(int i = min; i <= max; i++){
            acumulative = acumulative.add(densidad(i));
            System.out.println(densidad(i));
            // Found value
            if(acumulative.compareTo(uVal) >= 0) return i;
        }
        
        return max;
    }
    
}
