package domino;

import estruturaDeDados.Lista;
import estruturaDeDados.ListaIterator;
import utilitarios.ConsoleColor;
import utilitarios.Input;
import utilitarios.Output;

import java.util.Random;

public class Jogo {

    private Jogador jogador;
    private Jogador computador;
    private Jogador jogadorDaVez;
    private Lista<Peca> mesa;
    private Lista<Peca> monte;
    private Output output;
    private Input input;

    public Jogo(int maiorPeca) {
        this.jogador = new Jogador("Jogador");
        this.computador = new Jogador("Computador");
        this.mesa = new Lista<>();

        this.output = new Output();
        this.input = new Input();

        this.criarMonte(maiorPeca);
        this.distribuirPecas();
    }

    private void criarMonte(int limite){
        this.monte = new Lista<>();

        for(int i = 0; i <= limite; i++) {
            for(int j = i; j <= limite; j++) {
                monte.inserir(new Peca(i, j));
            }
        }
    }

    private void distribuirPecas(){
        int qtdInicial = Math.floorDiv(this.monte.getTamanho(), 4);

        Peca peca;
        for (int i = 0; i < qtdInicial; i++){
            peca = monte.remover(inteiroAleatorio(1, monte.getTamanho()));
            peca.setCor(ConsoleColor.ANSI_CYAN);
            jogador.getPecas().inserir(peca);

            peca = monte.remover(inteiroAleatorio(1, monte.getTamanho()));
            peca.setCor(ConsoleColor.ANSI_YELLOW);
            computador.getPecas().inserir(peca);
        }
    }

    public void iniciar() {
        this.executarPrimeiraJogada();
        this.executarPartida();
    }

    private void executarPartida() {
        boolean jogando = true;
        do{
            output.imprimirJogo(this.jogador, this.mesa);
            this.trocarJogadorDaVez();

            if (this.jogadorDaVez.equals(this.jogador)){
                this.executarTurnoDoJogador();
            } else {
                this.executarTurnoDoComputador();
            }

            if (this.jogadorDaVez.getPecas().getTamanho() == 0) {
                output.anunciarVencedor(this.jogadorDaVez);
                jogando = false;
            }

            if (this.monte.estaVazia()) {
                Lista<Integer> posicoesJogaveisJogador = this.obterPosicaoPecasJogaveis(this.jogador);
                Lista<Integer> posicoesJogaveisComputador = this.obterPosicaoPecasJogaveis(this.computador);
                if (posicoesJogaveisJogador.estaVazia() && posicoesJogaveisComputador.estaVazia()) {
                    PontuacaoPeca pontuacaoJogador = this.obterPontuacaoPeca(this.jogador.getPecas());
                    PontuacaoPeca pontuacaoComputador = this.obterPontuacaoPeca(this.computador.getPecas());

                    if (pontuacaoJogador.somaTotalValores == pontuacaoComputador.somaTotalValores) {
                        output.anunciarEmpate();
                    } else {
                        // Vençe o jogador com a menor pontuação somando os valores de todas as peças
                        output.anunciarVencedor(pontuacaoComputador.somaTotalValores > pontuacaoJogador.somaTotalValores ? this.jogador : this.computador);
                    }

                    jogando = false;
                }
            }

        } while(jogando);
    }

    private void executarTurnoDoComputador() {
        do {
            Lista<Integer> posicoesJogaveis = this.obterPosicaoPecasJogaveis(this.jogadorDaVez);
            if (posicoesJogaveis.estaVazia()){
                if (inteiroAleatorio(1, 2) != 1){
                    output.anunciarPassagemDaVez(this.jogadorDaVez);
                    break;
                }

                try {
                    this.comprarPeca(this.jogadorDaVez);
                    continue;
                } catch (UnsupportedOperationException e) {
                    output.anunciarPassagemDaVez(this.jogadorDaVez);
                    break;
                }
            }
            jogarPeca(this.jogadorDaVez, posicoesJogaveis.remover());
            break;
        } while (true);
    }

    private void executarTurnoDoJogador() {
        do {
            Lista<Integer> posicoesJogaveis = this.obterPosicaoPecasJogaveis(this.jogadorDaVez);
            if (posicoesJogaveis.estaVazia()){
                output.solicitarEscolhaDeAcao_NenhumaPecaJogavel();
                int acaoSelecionada = -1;
                try {
                    acaoSelecionada = input.obterInteiro();
                    if (acaoSelecionada == 2){
                        output.anunciarPassagemDaVez(this.jogadorDaVez);
                        break;
                    } else if (acaoSelecionada == 1) {
                        try {
                            this.comprarPeca(this.jogadorDaVez);
                            output.imprimirJogo(this.jogador, this.mesa);
                            continue;
                        } catch (UnsupportedOperationException e) {
                            output.anunciarPassagemDaVez(this.jogadorDaVez);
                            break;
                        }
                    } else {
                        output.anunciarOperacaoInvalida("Valor não corresponde a uma ação disponível");
                        continue;
                    }
                }
                catch (NumberFormatException e) {
                    output.anunciarOperacaoInvalida("valor digitado não é um número");
                    continue;
                }
            }
            output.imprimirLista("Peças na Mesa: ", this.mesa);
            output.imprimirLista_ComPosicaoDosNos("Mão do Jogador: ", this.jogadorDaVez.getPecas());
            output.solicitarEscolhaDePeca();
            try {
                jogarPeca(this.jogadorDaVez, input.obterInteiro());
            } catch (IllegalArgumentException e) {
                output.anunciarOperacaoInvalida(e.getMessage());
                continue;
            }
            break;
        } while(true);
    }

    private void executarPrimeiraJogada() {
        this.jogadorDaVez = (jogadorComeca()) ? this.jogador : this.computador;
        PontuacaoPeca pontuacaoPeca = obterPontuacaoPeca(this.jogadorDaVez.getPecas());
        output.anunciarPrimeiraJogada(this.jogadorDaVez);
        jogarPeca(this.jogadorDaVez, (pontuacaoPeca.maiorDupla != -1) ? pontuacaoPeca.posicaoMaiorDupla : pontuacaoPeca.posicaoMaiorValor);
    }

    private void trocarJogadorDaVez(){
        this.jogadorDaVez = (jogadorDaVez.equals(this.jogador)) ? this.computador : this.jogador;
        output.anuciarVezDoJogador(this.jogadorDaVez);
    }

    private int inteiroAleatorio(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private boolean jogadorComeca() {
        PontuacaoPeca pontuacaoJogador = obterPontuacaoPeca(this.jogador.getPecas());
        PontuacaoPeca pontuacaoComputador = obterPontuacaoPeca(this.computador.getPecas());

        if (pontuacaoJogador.maiorDupla != -1 || pontuacaoComputador.maiorDupla != -1) {
            return pontuacaoJogador.maiorDupla > pontuacaoComputador.maiorDupla;
        } else {
            return pontuacaoJogador.maiorValor > pontuacaoComputador.maiorValor;
        }
    }

    private class PontuacaoPeca {
        int maiorDupla;
        int maiorValor;
        int posicaoMaiorDupla;
        int posicaoMaiorValor;
        int somaTotalValores;

        public PontuacaoPeca(int maiorDupla, int maiorValor, int posicaoMaiorDupla, int posicaoMaiorValor, int somaValoresTotal) {
            this.maiorDupla = maiorDupla;
            this.maiorValor = maiorValor;
            this.posicaoMaiorDupla = posicaoMaiorDupla;
            this.posicaoMaiorValor = posicaoMaiorValor;
            this.somaTotalValores = somaValoresTotal;
        }
    }

    private PontuacaoPeca obterPontuacaoPeca(Lista<Peca> lista) {
        int maiorDupla = -1;
        int maiorValor = -1;
        int posicaoMaiorDupla = -1;
        int posicaoMaiorValor = -1;
        int somaTotalValores = 0;

        int pos = 1;
        ListaIterator<Peca> listaIterator = lista.obterIterator();

        do {
            Peca peca = listaIterator.obterObjeto();
            int valorA = peca.getValorA();
            int valorB = peca.getValorB();

            if (valorA == valorB && valorA > maiorDupla){
                maiorDupla = valorA;
                posicaoMaiorDupla = pos;
            }

            int valorPeca = valorA + valorB;
            if (valorPeca > maiorValor){
                maiorValor = valorPeca;
                posicaoMaiorValor = pos;
            }

            somaTotalValores += valorPeca;

            pos++;
        } while (listaIterator.temProximo());

        return new PontuacaoPeca(maiorDupla, maiorValor, posicaoMaiorDupla, posicaoMaiorValor, somaTotalValores);
    }

    private void jogarPeca(Jogador jogador, int posicaoPeca) throws IllegalArgumentException{
        Peca pecaJogada = jogador.getPecas().remover(posicaoPeca);
        if (mesa.estaVazia()){
            this.mesa.inserir(pecaJogada);
            this.output.anunciarJogada(jogador, pecaJogada);
        } else { // avaliar jogada
            int pontaA = mesa.obterInicio().getValorA();
            int pontaB = mesa.obterFim().getValorB();

            int pecaJogada_ValorA = pecaJogada.getValorA();
            int pecaJogada_ValorB = pecaJogada.getValorB();

            boolean podeJogarNaPontaA = pecaJogada_ValorA == pontaA
                                        || pecaJogada_ValorB == pontaA;
            boolean podeJogarNaPontaB = pecaJogada_ValorA == pontaB
                                        || pecaJogada_ValorB == pontaB;

            boolean escolheuA = true;
            if (podeJogarNaPontaA && podeJogarNaPontaB) {
                if (this.jogadorDaVez.equals(this.jogador)){
                    int acaoSelecionada = -1;
                    do{
                        output.solicitarEscolhaDePonta();
                        try {
                            acaoSelecionada = input.obterInteiro();
                            escolheuA = acaoSelecionada == 1;
                        }
                        catch (NumberFormatException e) { output.anunciarOperacaoInvalida("Valor digitado não é um número"); }
                    } while (acaoSelecionada != 1 && acaoSelecionada != 2);
                } else {
                    escolheuA = inteiroAleatorio(1, 2) == 1;
                }
            }

            if (podeJogarNaPontaA && escolheuA) {
                if (pecaJogada_ValorB != pontaA) {
                    pecaJogada.girar();
                }
                this.mesa.inserir(pecaJogada, 1);
                this.output.anunciarJogada(jogador, pecaJogada);
            } else if (podeJogarNaPontaB) {
                if (pecaJogada_ValorA != pontaB) {
                    pecaJogada.girar();
                }
                this.mesa.inserir(pecaJogada);
                this.output.anunciarJogada(jogador, pecaJogada);
            } else {
                jogador.getPecas().inserir(pecaJogada, posicaoPeca);
                throw new IllegalArgumentException("Peça não pode ser jogada em nenhum dos lados da mesa");
            }
        }
    }

    private void comprarPeca(Jogador jogador) throws UnsupportedOperationException {
        if (this.monte.estaVazia()){
            throw new UnsupportedOperationException("Monte está vazio.");
        }

        Peca pecaComprada = this.monte.remover(this.inteiroAleatorio(1, this.monte.getTamanho()));
        pecaComprada.setCor(jogador.equals(this.jogador) ? ConsoleColor.ANSI_CYAN : ConsoleColor.ANSI_YELLOW);
        jogador.getPecas().inserir(pecaComprada);
        this.output.anunciarCompra(jogador);
    }

    private Lista<Integer> obterPosicaoPecasJogaveis(Jogador jogador) {
        Lista<Integer> posicoesJogaveis = new Lista<>();

        int pontaA = mesa.obterInicio().getValorA();
        int pontaB = mesa.obterFim().getValorB();

        int pos = 1;
        ListaIterator<Peca> listaIterator = jogador.getPecas().obterIterator();

        do {
            Peca peca = listaIterator.obterObjeto();
            int valorA = peca.getValorA();
            int valorB = peca.getValorB();

            boolean podeJogarNaPontaA = valorA == pontaA
                    || valorB == pontaA;
            boolean podeJogarNaPontaB = valorA == pontaB
                    || valorB == pontaB;

            if (podeJogarNaPontaA || podeJogarNaPontaB) {
                posicoesJogaveis.inserir(pos);
            }

            pos++;
        } while (listaIterator.temProximo());

        return posicoesJogaveis;
    }

}
