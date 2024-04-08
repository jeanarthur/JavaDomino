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

    public void imprimirJogo(Jogador jogador, Lista<Peca> mesa) {
        this.imprimirLista("Mesa: ", mesa);
        this.imprimirLista("Jogador: ", jogador.getPecas());
    }

    public void anunciarJogada(Jogador jogador, Peca peca) {
        System.out.printf("%s jogou a peça %s na mesa.\n", jogador.getNome(), peca.toString());
    }

    public void anuciarVezDoJogador(Jogador jogador) {
        System.out.printf("Vez do %s jogar.\n", jogador.getNome());
    }

    public void anunciarVencedor(Jogador jogador) {
        System.out.printf("%s venceu o joga.\n", jogador.getNome());
    }

    public void solicitarEscolhaDePeca(){
        System.out.print("Escolha uma peca para jogar: ");
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
