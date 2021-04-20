

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
        int k = 0;

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
        
        String[] contasTempo = new String[contAbre + 1];
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
                    if (contasTempo[contAbre] == null) {
                        contasTempo[contAbre] = String.valueOf(d);
                    } else {
                        contasTempo[contAbre] = contasTempo[contAbre] + d;

                    }
                }

            }

            for (i = 0; i < contas.length; i++) {
                contas[i] = contasTempo[contas.length-i - 1];
            }

        } else {
            System.out.println("A Expressão esta incorreta" + contAbre + contFecha);
        }
        for ( i = 0;  i< contas.length; i++) {
            System.out.println(contas[i]+"\n\n"+contasTempo[i]+"\n\n\n");
            
        }
        
    }
}

/*
----------6 * 8 -2 / 4

- identificar os operadores ->
percorre o char buscando * ou / 
se encontrar ele vai realizar a operação com a posição i + 1 e i -1 e substituir a posição do vetor pelo resultado 
	-> se nao tiver i - 1 retorna erro
	-> se nao tiver i + 1 espera o prox parenteses e junta tudo dps
	se tiver os dois ele prossegue:
depois ele busca por + e - e realiza a operacao i = 1 e i - 1
	-> se nao tiver i - 1 retorna erro
	-> se nao tiver i + 1 espera o prox parenteses e junta tudo dps
	-> se tiver os dois ele realiza o resto da expressão.
*/