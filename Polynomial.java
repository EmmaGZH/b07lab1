import java.io.*;
import java.lang.*;


public class Polynomial {
    double coefficients[];
    int exponents[];

    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0.0;
        exponents = new int[1];
    }

    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public int contains(int[] exp_array, int exp){
        int length = exp_array.length;
        int contain = 1;
        for (int i=0; i<length; i++){
            if (exp_array[i] == exp){
                contain = 0;
            }
        }
        return contain;
    }

    public int find_ind(int[] exp_array, int exp){
        int length = exp_array.length;
        int ind = -1;
        for(int i=0; i<length; i++){
            if(exp_array[i] == exp);
            ind = i;
        }
        return ind;
    }

    public Polynomial add(Polynomial other){
        int length = coefficients.length;
        for (int i=0; i<other.coefficients.length; i++){
            if(contains(exponents, other.exponents[i])==1){
                length++;
            }
        }
        int result_exp[] = new int[length];
        for (int i=0; i<exponents.length; i++){
            result_exp[i]=exponents[i];
        }
        for (int i=0; i<other.exponents.length; i++){
            if(contains(exponents, other.exponents[i])==1){
                result_exp[i+exponents.length-1]=other.exponents[i];
            }
        }
        double result_coeff[] = new double[length];
        for (int i=0; i<length; i++){
            result_coeff[i]=0;
        }
        for (int i=0; i<length; i++){
            if(contains(exponents, result_exp[i])==0){
                result_coeff[i]=result_coeff[i]+coefficients[find_ind(exponents, result_exp[i])];
            }
            if(contains(other.exponents, result_exp[i])==0){
                result_coeff[i]=result_coeff[i]+other.coefficients[find_ind(other.exponents, result_exp[i])];
            }
        }
        Polynomial result = new Polynomial(result_coeff, result_exp);
        return result;
    }

    public double evaluate(double x_value){
        double evaluated = 0.0;
        for (int i=0; i<coefficients.length; i++){
            evaluated = evaluated + coefficients[i] * Math.pow(x_value, exponents[i]);
        }
        return evaluated;
    }

    public boolean hasRoot(double value){
        double evaluated;
        evaluated = evaluate(value);
        return evaluated == 0;
    }
    public Polynomial multiply(Polynomial other){
        int max_length = coefficients.length * other.coefficients.length;
        int[] ml_result_exp = new int[max_length];
        double[] ml_result_coeff = new double[max_length];
        for(int i=0; i<max_length; i++){
            ml_result_exp[i]=0;
            ml_result_coeff[i]=0;
        }
        int len = 0;
        for (int i=0; i<coefficients.length; i++){
            for (int j=0; j<other.coefficients.length; j++){
                int exp=exponents[i] + other.exponents[j];
                double coeff=coefficients[i] * other.coefficients[j];
                if(contains(ml_result_exp, exp)==0){
                    int index=find_ind(ml_result_exp, exp);
                    ml_result_coeff[index]=ml_result_coeff[index] + coeff;
                }
                else{
                    ml_result_exp[len]=exp;
                    ml_result_coeff[len]=coeff;
                    len++;
                }
            }
        }
        int length = 0;
        int index = 0;
        while(ml_result_exp[index]!=0){
            length++;
            index++;
        }
        int[] result_exp = new int[length];
        double[] result_coeff = new double[length];
        for(int i=0; i<length; i++){
            result_exp[i]=ml_result_exp[i];
            result_coeff[i]=ml_result_coeff[i];
        }
        Polynomial result = new Polynomial(result_coeff, result_exp);
        return result;
    }

    public Polynomial(File file_name){
        String str = file_name.nextLine();
        String[] poly = str.split("[- +]+");
        int length = poly.length;
        double[] result_coeff = new double[length];
        int[] result_exp = new int[length];
        for(int i=0; i<length; i++){
            String[] element = poly[i].split("x");
            double coefficient = Double.parseDouble(element[0]);
            int exponent;
            if(element.length>1){
                exponent = Integer.parseInt(element[1]);
            }
            int ind = str.indexOf(poly[i]);
            if (Character.compare(str.charAt(ind-1),'-') == 0){
                coefficient = coefficient * -1;
            }
            result_coeff[i] = coefficient;
            result_exp[i] = exponent;
        }
        this.coefficients=result_coeff;
        this.exponents=result_exp;
    }
    
    void saveToFile(String file_name){
        PrintWriter out = new PrintWriter(file_name);
        int length = coefficients.length;
        String[] str = new String[length];
        for(int i=0; i<length; i++){
            double coefficient = coefficients[i];
            int exponent = exponents[i];
            if (exponent == 0){
                str[i] = Double.toString(coefficient);
            }
            else{
                str[i] = Double.toString(coefficient) + "x" + Integer.toString(exponent);
            }
        }
        String poly_s = str[0];
        for(int i=1; i<length; i++){
            if(Character.compare(str[i].charAt(0),'-') == 0){
                poly_s = poly_s + str[i];
            }
            else{
                poly_s = poly_s + "+" + str[i];
            }
        }
       out.println(poly_s);
       out.close(); 
    }
}
