
public class App {
    public static void main(String[] args) {

        String expressao = "(4*8+2*(1+3)+(5*(8.4/2)+2/(4/4)))"; // arrumar 6.2.2.3 -> error
        char c[] = new char[expressao.length()];
        String[] pegaExpressao = new String[expressao.length()];
        double[][] numeros = new double[expressao.length()][expressao.length()];
        String armazenaNumero = "";
        int contAbreFecha = 0;
        int contOperadores = 0;
        int contCaracInvalido = 0;
        int maiorNumAbreFecha = 0;
        int cont = 0;
        int erro = 0;
        int i = 0;
        int k = 0;
        for (i = 0; i < expressao.length(); i++) {
            c[i] = expressao.charAt(i);
        }
        i = 0;
        for (char d : c) {
            if (d == '+' || d == '-' || d == '*' || d == '/' || d == '.') {
                contOperadores++;

            } else {
                contOperadores = 0;
            }
            if (contOperadores > 1) {
                break;
            }

            if (d != '+' && d != '-' && d != '*' && d != '/' && d != '.' && d != '(' && d != ')' && d != '0' && d != '1'
                    && d != '2' && d != '3' && d != '4' && d != '5' && d != '6' && d != '7' && d != '8' && d != '9') {
                System.out.println(d);
                contCaracInvalido++;
                break;

            }

            if (d == '(') {
                contAbreFecha++;

            } else if (d == ')') {
                contAbreFecha--;

            }

            if (contAbreFecha < 0) {
                break;

            }
            if (contAbreFecha > maiorNumAbreFecha) {
                maiorNumAbreFecha = contAbreFecha;
            }

        }
        if (contAbreFecha != 0) {
            System.out.println("O numero de parenteses é inválido");
            erro++;
        }
        if (contOperadores > 1) {
            System.out.println("Não é permitida a entrada de dois operadores matemáticos seguidos.");
            erro++;
        }
        if (contCaracInvalido != 0) {
            System.out.println("Caracter inserido não é válido.");
            erro++;
        }

        if (erro == 0) {
            System.out.println("executa a expressão");

            for (int j = maiorNumAbreFecha; j >= 0; j--) {
                for (char d : c) {

                    if (d == '(') {
                        contAbreFecha++;

                    } else if (d == ')') {
                        contAbreFecha--;

                    }

                    if (contAbreFecha == j) {

                        if (pegaExpressao[cont] == null) {

                            pegaExpressao[cont] = Character.toString(d);

                        } else {

                            pegaExpressao[cont] = pegaExpressao[cont] + d;

                            if (d == '(' || d == ')') {

                                for (k = 0; k < pegaExpressao[cont].length(); k++) {

                                    if (pegaExpressao[cont].charAt(k) != '+' && pegaExpressao[cont].charAt(k) != '-'
                                            && pegaExpressao[cont].charAt(k) != '*'
                                            && pegaExpressao[cont].charAt(k) != '/'
                                            && pegaExpressao[cont].charAt(k) != '('
                                            && pegaExpressao[cont].charAt(k) != ')') {

                                        char tempoChar = pegaExpressao[cont].charAt(k);
                                        armazenaNumero = armazenaNumero + Character.toString(tempoChar);

                                    } else {
                                        if (armazenaNumero != "") {
                                            System.out.println(armazenaNumero);
                                            numeros[cont][i] = Double.parseDouble(armazenaNumero);
                                            i++;
                                        }
                                        armazenaNumero = "";

                                    }

                                }
                                System.out.println(armazenaNumero);
                                if (armazenaNumero != "") {
                                    numeros[cont][i] = Double.parseDouble(armazenaNumero);
                                    i++;
                                }
                                armazenaNumero = "";
                                cont++;
                                i = 0;

                            }
                        }

                    }

                }

            }
            for (int j = 0; j < cont; j++) {

                for (k = 0; k < pegaExpressao[j].length(); k++) {
                    System.out.println(numeros[j][k]);
                }
                System.out.println("\n\n");
            }

            for (int j = 0; j < cont; j++) {

                System.out.println(pegaExpressao[j]);

            }

        } else {
            System.out.println("Não executa a expressão");
        }

    }
}

/*
 * ----------6 * 8 -2 / 4 contas[i] = "    "+String.valueOf(mult); - identificar
 * os operadores -> percorre o char buscando * ou / se encontrar ele vai
 * realizar a operação com a posição i + 1 e i -1 e substituir a posição do
 * vetor pelo resultado -> se nao tiver i - 1 retorna erro -> se nao tiver i + 1
 * espera o prox parenteses e junta tudo dps se tiver os dois ele prossegue:
 * depois ele busca por + e - e realiza a operacao i = 1 e i - 1 -> se nao tiver
 * i - 1 retorna erro -> se nao tiver i + 1 espera o prox parenteses e junta
 * tudo dps -> se tiver os dois ele realiza o resto da expressão.
 * 
 */