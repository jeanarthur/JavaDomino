package estruturaDeDados;

public class ListaIterator<T> {

    private No<T> noAtual;

    public ListaIterator(No<T> primeiro){
        this.noAtual = primeiro;
    }

    public boolean temProximo(){
        this.noAtual = this.noAtual.proximo;
        return this.noAtual != null;
    }

    public T obterObjeto(){
        return this.noAtual.getObjeto();
    }

}
