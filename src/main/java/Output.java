public class Output {

    public void imprimirLista(Lista lista){
        ListaIterator listaIterator = lista.obterIterator();

        if (lista.estaVazia()) { return; }

        do { System.out.print(listaIterator.obterObjeto().toString()); }
        while (listaIterator.temProximo());

        System.out.println();
    }

}
