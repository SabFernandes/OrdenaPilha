class TadFilaExpressao {

    // atributos
    private String[] dados;
    private int tamMax;
    private int posFinal;
    private int maiorNumAbreFecha;

    // construtor
    public TadFilaExpressao(int tamFila) {
        this.dados = new String[tamFila];
        this.tamMax = tamFila;
        this.posFinal = -1;
        this.maiorNumAbreFecha = 0;
    }

    // metodos

    // vazia
    public boolean isEmpty() {
        if (this.posFinal == -1) {
            return true;
        } else {
            return false;
        }
    }

    // cheia
    public boolean isFull() {
        if (this.posFinal == (this.tamMax - 1)) {
            return true;
        } else {
            return false;
        }
    }

    // enqueue
    public void enqueue(String valor) {
        if (isFull()) {
            System.out.println("Não inseriu elemento - fila cheia");
        } else {
            this.dados[this.posFinal + 1] = valor;
            this.posFinal++;
        }
    }

    // dequeue
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Não existe dado - fila vazia");
            return "0";
        } else {
            String elemento = this.dados[0];
            for (int i = 0; i < this.posFinal; i++) {
                this.dados[i] = this.dados[i + 1];
            }
            this.posFinal--;
            return elemento;
        }
    }

    // peek
    public String peek() {
        return this.dados[0];
    }

    // tamanho
    public int length() {// pega o tamanho da fila :)
        return this.posFinal + 1;
    }

    public void imprimeExpressao() {

        System.out.println("--------------------------");
        if (isEmpty()) {
            System.out.println("Nada a imprimir - fila vazia");
        } else {
            for (int i = 0; i <= this.posFinal; i++) {
                System.out.printf(" %s ", this.dados[i]);
            }
        }
        System.out.printf("\n");
        System.out.println("--------------------------");
    }

    public int valida() {
        int contAbreFecha = 0;
        int contOperadores = 0;
        int erro = 0;
        for (String d : this.dados) {
            if (d != null) {
                if (d.equals("+") == true || d.equals("-") == true || d.equals("*") == true || d.equals("/") == true) {
                    contOperadores++;

                } else {
                    contOperadores = 0;
                }
                if (contOperadores > 1) {
                    System.out.println("Não é permitida a entrada de dois operadores matemáticos seguidos.");
                    erro++;
                    break;
                }

                if (d.equals("+") == false && d.equals("-") == false && d.equals("*") == false && d.equals("/") == false
                        && d.equals("(") == false && d.equals(")") == false && !d.matches("^[0-9]*[.]{0,1}[0-9]*$")) {
                    System.out.println("Caracter inserido("+d+") não é válido.");
                    contAbreFecha=0;
                    erro++;
                    break;

                }

                if (d.equals("(") == true) {
                    contAbreFecha++;

                } else if (d.equals(")") == true) {
                    contAbreFecha--;

                }

                if (contAbreFecha < 0) {
                    break;

                }
                if (contAbreFecha > this.maiorNumAbreFecha) {
                    this.maiorNumAbreFecha = contAbreFecha;
                }
                
            }

        }
        if (contAbreFecha != 0) {
            System.out.println("O numero de parenteses é inválido");
            erro++;
        }
        return erro;
    }

    public int getMaiorNumAbreFecha() {
        return this.maiorNumAbreFecha;

    }

    public void calculaExpressao() {
        for (int j = getMaiorNumAbreFecha(); j > 0; j--) {
            int contaExpressao = 0;
            int tamanhoExpr = length();
            int contTamanhoExpr = 0;
            String tempOperador = "";
            TadFilaExpressao expressao = new TadFilaExpressao(this.length());

            int removido = 0;

            while (contaExpressao < j) {
                tempOperador = this.dequeue();
                // Separa os parenteses até achar o que está mais no fundo.
                if (tempOperador.equals("(") == true) {
                    contaExpressao++;// cada vez que um parenteses abre, ele soma um, ate ficar = ao mais
                                     // alto(getMaiorNumAbreFecha)
                }
                if (contaExpressao < j) {
                    enqueue(tempOperador);// depois ele coloca o valor tirado de volta na fila
                    contTamanhoExpr++;
                }

            }
            contTamanhoExpr++;

            expressao.enqueue(tempOperador);
            removido = 1;

            // Depois de retirar(dequeue) os parenteses mais da esquerda, ele separa
            // exatamente o parenteses que esta mais no fundo(ultimo).
            while (this.peek().equals(")") == false) {
                expressao.enqueue(this.dequeue());// Ele da um enqueue na expressao a ser calculada e retira a expressao
                                                  // da
                                                  // principal até achar um parenteses fechando ')'
                removido++;// serve para diminuir o tamanho da fila ???
            }

            expressao.enqueue(this.dequeue());// coloca na expressao o parenteses fechando pra "limpar"a expressao
                                              // principal

            this.enqueue(calculoOperacao(expressao));// executa a conta e coloca de volta na fila principal.

            for (int i = 0; i < (tamanhoExpr - (contTamanhoExpr + removido)); i++) {
                this.enqueue(this.dequeue());// Ele coloca a fila na ordem natural dela(ordem q a expressao é dada).
            }
        }

    }

    public String calculoOperacao(TadFilaExpressao conta) {

        String numero1 = "0";
        String numero2 = "0";
        String operador = "";
        double resultado = 0;

        int qtdsoma = 0;
        int qtdsub = 0;
        int qtddiv = 0;
        int qtdmult = 0;

        // checar operações existentes
        for (int i = 0; i < conta.length(); i++) {
            operador = conta.dequeue();// ele tira o primeiro valor da fila e compara com os operadores, depois ele
                                       // retorna pra fila, até completar uma volta
            if (operador.equals("-") == true) {
                qtdsub++;

            }
            if (operador.equals("+") == true) {
                qtdsoma++;

            }
            if (operador.equals("/") == true) {
                qtddiv++;

            }
            if (operador.equals("*") == true) {
                qtdmult++;

            }
            conta.enqueue(operador);
        }

        while ((qtdmult > 0) || (qtddiv > 0)) {// como a multiplicação e a divi sao prioridades, ele faz esse while pra
                                               // fazer todas as mult e divs do parenteses.

            for (int i = 1; i < conta.length(); i++) {// esse eh o tamanho da expressao que ele separou
                                                      // anteriormente(tamanho da expressao = entre um '(' e um ')')

                numero1 = conta.dequeue();// armazena o primeiro valor e retira, até encontrar um operador, qnd
                                          // encontrar um operador, o valor armazenado será o 'numero 1'.
                if (conta.peek().equals("*") == true) {// compara o primeiro valor
                    conta.dequeue();// retira o operador
                    numero2 = conta.dequeue();// retira o segundo numero
                    resultado = Double.parseDouble(numero1) * Double.parseDouble(numero2);
                    conta.enqueue(Double.toString(resultado));// coloca o resultado na fila

                    qtdmult--;
                }

                else if (conta.peek().equals("/") == true) {
                    conta.dequeue();
                    numero2 = conta.dequeue();
                    resultado = Double.parseDouble(numero1) / Double.parseDouble(numero2);

                    conta.enqueue(Double.toString(resultado));

                    qtddiv--;
                }

                else {
                    conta.enqueue(numero1);// se ele nao encontra * ou / ele coloca no numero de volta na fila

                }
            }

        }

        while ((qtdsoma > 0) || (qtdsub > 0)) {// Depois de encontras as * e / ele repete o processo para + e -

            for (int i = 1; i < conta.length(); i++) {

                numero1 = conta.dequeue();

                if (conta.peek().equals("+") == true) {
                    conta.dequeue();
                    numero2 = conta.dequeue();
                    resultado = Double.parseDouble(numero1) + Double.parseDouble(numero2);
                    conta.enqueue(Double.toString(resultado));

                    qtdsoma--;
                } else if (conta.peek().equals("-") == true) {
                    conta.dequeue();
                    numero2 = conta.dequeue();
                    resultado = Double.parseDouble(numero1) - Double.parseDouble(numero2);

                    conta.enqueue(Double.toString(resultado));

                    qtdsub--;
                } else {
                    conta.enqueue(numero1);
                    numero1 = operador;
                }
            }
        }
        String resultadorfinal = conta.dequeue();
        while ((resultadorfinal.matches("^[(-)]*$"))) {
            resultadorfinal = conta.dequeue();
        }

        return resultadorfinal;

    }

}