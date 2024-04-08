public class Main {

    public static void main(String[] args) {

        testeJogo();

    }

    private static void testeJogo(){
        Jogo jogo = new Jogo(6);

        jogo.iniciar();

    }

    private static void testeLista(){
        Lista<Peca> lista = new Lista<>();

        Peca _00 = new Peca(0, 0);
        Peca _01 = new Peca(0, 1);
        Peca _02 = new Peca(0, 2);

        Output output = new Output();
        output.imprimirLista(lista);

        lista.inserir(_00);
        output.imprimirLista(lista);

        lista.inserir(_01, 1);
        output.imprimirLista(lista);

        lista.inserir(_02, 2);
        output.imprimirLista(lista);

        lista.remover(2);
        output.imprimirLista(lista);

        lista.remover();
        output.imprimirLista(lista);

        lista.remover(1);
        output.imprimirLista(lista);
    }

}
