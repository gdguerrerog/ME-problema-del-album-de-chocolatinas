/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 *
 * @author German le yo
 */
public class Binomial implements DistribucionAleatoria{
    
    private final double p,q;
    private final int n;
    
    /**
     * Constructor de una distribución binomial.
     * Es recomendable que n no sea muy grande pues debido a la precisió computacional, algunso valores extremos
     * son imposibles de obtener
     * @param n Numero de ensallos
     * @param p Probabiblidad de éxito
     */
    public Binomial(int n, double p){
        if(p > 1 || p < 0) throw new IllegalArgumentException("Ilegal p: " + p);
        this.p = p;
        this.n = n;
        q = 1 - p;
    }

    /**
     * Función de densidad de la distribucion binomial.
     * Calcula (n C x)*p^(x)*q^(n - x)
     * @param x Realización a calcular su probabilidad
     * @return Valor de la función de densidad de la distribución binomial
     * para el valor x
     */
    public BigDecimal densidad(int x){
        if(x > n) return BigDecimal.ZERO;
        int initial;
        // Para hacer la menor cantidad demultipliacaiónes posibles
        if(x > n - x) initial = x;
        else initial = n - x;
        
        BigInteger combination = BigInteger.ONE;
        
        // n!/((n - r)!*r!)
        for(int i = initial + 1; i <= n; i++) combination = combination.multiply(BigInteger.valueOf(i));
        for (int i = 2; i <= n - initial; i++) combination = combination.divide(BigInteger.valueOf(i));
        
        BigDecimal answer = new BigDecimal(combination);
        BigDecimal pVal = BigDecimal.valueOf(p), qVal = BigDecimal.valueOf(q);
        // (n C x)*p^(x)*q^(n-x)
        for (int i = 0; i < x; i++) answer = answer.multiply(pVal, MathContext.UNLIMITED);
        for (int i = 0; i < n - x; i++) answer = answer.multiply(qVal, MathContext.UNLIMITED);

        //System.out.printf("(%d C %d)*%f^(%d)*%f^(%d) = %s\n", n, x, p, x, q, n - x, answer);
        
        return answer;
    }
    
    /**
     * Calcula un valor aleatorio con una distribución binomial.
     * Suma continuamente los valores obtenidos de la función de densidad
     * para obtener su valor en la de distribución. Cuando este valor de 
     * la función de distribución supere un número aleatorio u, retornará
     * el valor calculado.
     * @return Un número aleatorio con una disribución binomial
     */
    @Override
    public int getRandomInt() {
        double u = random.nextDouble();
        BigDecimal uVal = BigDecimal.valueOf(u);
        BigDecimal acumulative = BigDecimal.ZERO, density;
        
        for(int i = 1; i < n; i++){
            density = densidad(i);
            acumulative = acumulative.add(density);
            // Found value
            if(acumulative.compareTo(uVal) >= 0) return i;
        }
        
        
        return n;
    }
    
}
