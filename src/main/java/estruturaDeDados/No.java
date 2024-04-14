package estruturaDeDados;

public class No<T> {

    private T objeto;
    No<T> anterior;
    No<T> proximo;

    public No(T objeto) {
        this.objeto = objeto;
    }

    public No(T objeto, No<T> anterior) {
        this.objeto = objeto;
        this.anterior = anterior;
    }

    public No(T objeto, No<T> anterior, No<T> proximo) {
        this.objeto = objeto;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public T getObjeto() {
        return objeto;
    }
}
