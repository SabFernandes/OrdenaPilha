import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira a expressão a ser cálculada.");
        String expressao = sc.nextLine();
        //String expressao = "(4+8*(2+2*9/2))"; // arrumar 6.2.2.3 -> error
        TadFilaExpressao fila = new TadFilaExpressao((expressao.length()*2));
        String tempoNum = "";
        int erro = 0;
        int i = 0;

        for (i = 0; i < expressao.length(); i++) {
            if(Character.toString(expressao.charAt(i)).equals(" ")==false){
                if (Character.toString(expressao.charAt(i)).matches("^[0-9]*[.]{0,1}[0-9]*$")) {
                    tempoNum = tempoNum + Character.toString(expressao.charAt(i));
                } else {
                    if (tempoNum.equals("") == true) {
                        fila.enqueue(Character.toString(expressao.charAt(i)));
                    } else {
                        fila.enqueue(tempoNum);
                        tempoNum = "";
                        i--;
                    }
                }
            }          
        }
        erro = fila.valida();
        if (erro == 0) {
            System.out.println("executa a expressão");
                fila.calculaExpressao();
                fila.imprimeExpressao();

            
        }
    }
}

