/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemaChocolatinas.DistribucionesAleatorias;

import static ProblemaChocolatinas.DistribucionesAleatorias.DistribucionAleatoria.random;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 *
 * @author German le yo
 */
public class GeometricaTruncada implements DistribucionAleatoria{

    public final int min, max;
    public final double p, q;
    private final BigDecimal probabilidadIntervalo;
    private final BigDecimal qVal;
    
    private static BigDecimal E = null;

    /**
     * Constructor de una variable aleatoria Geométrica Truncada.
     * @param p Probabilidad de exito del experiento de bernoulli
     * @param min Valor minimo de truncamiento de la variable (inclusivo)
     * @param max Valor máximo de truncamiento de la variable (inclusivo)
     */
    public GeometricaTruncada(double p, int min, int max){
        
        if(E == null) E = BigDecimal.valueOf(Math.E);
        
        this.p = p;
        this.q = 1 - p;
        this.min = min;
        this.max = max;
        
        qVal = BigDecimal.valueOf(q);
        
        /**
         * La probabilidad de que x E [min, max] es q^(min - 1) - q^max
         */
        this.probabilidadIntervalo = qVal.pow(min - 1).subtract(qVal.pow(max));
    }
    
    public BigDecimal distribucion(int x){
        if(x < min || x > max) return BigDecimal.ZERO;
        return qVal.pow(x - 1).multiply(BigDecimal.valueOf(p)).divide(this.probabilidadIntervalo, MathContext.DECIMAL128);
    }
    
    
    @Override
    public int getRandomInt() {
        double u = random.nextDouble();
        BigDecimal uVal = BigDecimal.valueOf(u);

        BigDecimal densidad = BigDecimal.ZERO;
        for (int i = min; 1 <= max; i++) {
            densidad = densidad.add(distribucion(i));
            if(densidad.compareTo(uVal) >= 0) return i;
        }
        
        return max;
    }
    
}