package src;

public class App {
    public static void main(String[] args) {

        String expressao = "6*2+(8-2*(5+(4*2/8)))";
        char c[] = new char[expressao.length()];

        int contAbre = 0;
        int contFecha = 0;
        // int enumeraAberto = 0;
        // int enumeraFechado = 0;
        int i = 0;
        int j = 0;

        for (i = 0; i < expressao.length(); i++) {
            c[i] = expressao.charAt(i);
        }
        i = 0;
        for (char d : c) {

            if (d == '(') {

                contAbre++;

            } else if (d == ')') {
                contFecha++;

            }

        }
        String[] contas = new String[contAbre + 1];
        if (contAbre == contFecha) {
            System.out.println("A Expressão esta correta" + contAbre + contFecha);
            contAbre = 0;
            contFecha = 0;
            for (char d : c) {

                if (d == '(') {

                    contAbre++;

                } else if (d == ')') {
                    contFecha++;

                } else {
                    if (contas[contAbre] == null) {
                        contas[contAbre] = String.valueOf(d);
                    } else {
                        contas[contAbre] = contas[contAbre] + d;

                    }
                }

            }
        } else {
            System.out.println("A Expressão esta incorreta" + contAbre + contFecha);
        }

        System.out.println(c);

    }
}
