
import java.util.ArrayList;
public class Teste {
    public static void main(String[] args) {
        String toCalculate = "123+98-79÷25";
        int operator_count = 0;
        ArrayList<Character> operators = new ArrayList<>();
        for (int i=0; i < toCalculate.length(); i++){
             if (toCalculate.charAt(i) == '+'  toCalculate.charAt(i) == '-' 
                 toCalculate.charAt(i) == '' || toCalculate.charAt(i) == '÷' ) {
             operator_count++;  
             operators.add(toCalculate.charAt(i)); 
         }
     }
     System.out.println("");
     System.out.println("Return Value :" );

     String[] retval = toCalculate.split("\+|\-|\|\÷", operator_count + 1);

    int num1 = Integer.parseInt(retval[0]);
    int num2 = 0;
    int j = 0;
    for (int i = 1; i < retval.length; i++) {

        num2 = Integer.parseInt(retval[i]);
        char operator = operators.get(j);
        if (operator == '+') {
            num1 = num1 + num2;

        }else if(operator == '-'){
            num1 = num1 - num2;
        }else if(operator == '÷'){
            num1 = num1 / num2;
        }else{
            num1 = num1 * num2;
        }
        j++;
    }
    System.out.println(num1);   // Prints the result value
    }

}