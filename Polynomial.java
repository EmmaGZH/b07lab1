public class Polynomial {
    double coefficients[];

    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0.0;
    }

    public Polynomial(double[] coefficients){
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other){
        int length = Math.max(coefficients.length, other.coefficients.length);
        double coeff_sum[] = new double[length];
        for (int i=0; i<length; i++){
            coeff_sum[i]=0;
        }
        for (int i=0; i<coefficients.length; i++){
            coeff_sum[i] = coeff_sum[i] + coefficients[i];
        }
        for (int i=0; i< other.coefficients.length; i++){
            coeff_sum[i] = coeff_sum[i] + other.coefficients[i];
        }
        Polynomial result = new Polynomial(coeff_sum);
        return result;
    }

    public double evaluate(double x_value){
        double evaluated = 0.0;
        for (int i=0; i<coefficients.length; i++){
            if (coefficients[i] != 0){
                evaluated = evaluated + coefficients[i] * Math.pow(x_value, i);
            }
        }
        return evaluated;
    }

    public boolean hasRoot(double value){
        double evaluated;
        evaluated = evaluate(value);
        return evaluated == 0;
    }
}
