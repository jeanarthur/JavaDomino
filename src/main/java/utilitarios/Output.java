package utilitarios;

import domino.Jogador;
import domino.Peca;
import estruturaDeDados.Lista;
import estruturaDeDados.ListaIterator;

public class Output {

    public void imprimirLista(Lista lista){
        ListaIterator listaIterator = lista.obterIterator();

        if (lista.estaVazia()) { return; }

        do { System.out.print(listaIterator.obterObjeto().toString()); }
        while (listaIterator.temProximo());

        System.out.println();
    }

    public void imprimirLista(String prefixo, Lista lista){
        ListaIterator listaIterator = lista.obterIterator();

        if (lista.estaVazia()) { return; }

        System.out.print(prefixo);
        do { System.out.print(listaIterator.obterObjeto().toString()); }
        while (listaIterator.temProximo());

        System.out.println();
    }

    public void imprimirLista_ComPosicaoDosNos(String prefixo, Lista lista){
        ListaIterator listaIterator = lista.obterIterator();

        if (lista.estaVazia()) { return; }

        System.out.print(prefixo);
        int pos = 1;
        do { System.out.printf("(%d)%s ", pos++, listaIterator.obterObjeto().toString()); }
        while (listaIterator.temProximo());

        System.out.println();
    }

    public void imprimirJogo(Jogador jogador, Lista<Peca> mesa) {
        System.out.println("+==========================================================================+");
        System.out.println("|                        Estado atual do jogo                              +");
        System.out.println("+==========================================================================+");
        this.imprimirLista("| Mesa: ", mesa);
        this.imprimirLista("| Mão do Jogador: ", jogador.getPecas());
        System.out.println("+==========================================================================+");
        System.out.println();
    }

    public void anunciarJogada(Jogador jogador, Peca peca) {
        System.out.printf("%s jogou a peça %s na mesa.\n", jogador.getNome(), peca.toString());
    }

    public void anuciarVezDoJogador(Jogador jogador) {
        System.out.printf("Vez do %s jogar.\n", jogador.getNome());
    }

    public void anunciarVencedor(Jogador jogador) {
        System.out.printf("%s venceu o jogo!\n", jogador.getNome());
    }

    public void anunciarEmpate() {
        System.out.printf("O jogo encerrou empatado pois ambos os jogadores não podem jogar e possuem a mesma pontuação!\n");
    }

    public void anunciarPrimeiraJogada(Jogador jogador) {
        System.out.printf("O %s inicia por possuir a maior peça em mãos.\n", jogador.getNome());
    }

    public void solicitarEscolhaDePeca(){
        System.out.print("Escolha uma peça para jogar (digite a posição da peça): ");
    }

    public void solicitarEscolhaDeAcao_NenhumaPecaJogavel(){
        System.out.print("(1) Comprar \n(2) Passar a vez \nEscolha uma ação: ");
    }

    public void solicitarEscolhaDePonta(){
        System.out.print("(1) Esquerda \n(2) Direita \nEscolha um lado para colocar a peça: ");
    }

    public void anunciarCompra(Jogador jogador) {
        System.out.printf("%s comprou uma peça do monte.\n", jogador.getNome());
    }

    public void anunciarPassagemDaVez(Jogador jogador) {
        System.out.printf("%s passou a vez.\n", jogador.getNome());
    }

    public void anunciarOperacaoInvalida(String operacao) {
        System.out.printf("Operação inválida: %s, tente novamente.\n", operacao);
    }
}
