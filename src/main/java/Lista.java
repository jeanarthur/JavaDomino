public class Lista<T> {

    No<T> inicio;
    No<T> fim;
    int tamanho;

    public Lista() {
        this.tamanho = 0;
    }

    public boolean estaVazia(){
        return this.inicio == null;
    }

    public ListaIterator obterIterator(){
        return new ListaIterator<>(this.inicio);
    }

    public void inserir(T objeto){
        No<T> noAdicionado;

        if (this.estaVazia()){
            noAdicionado = new No<>(objeto);
            this.inicio = noAdicionado;
        } else {
            noAdicionado = new No<>(objeto, this.fim);
            this.fim.anterior = this.fim;
        }

        this.fim = noAdicionado;
        this.tamanho++;
    }

    public void inserir(T objeto, int posicao) throws IllegalArgumentException {
        if (posicao < 1 || posicao > this.tamanho){
           throw new IllegalArgumentException("Valor fora do permitido.");
        }
        No<T> noAdicionado;

        if (posicao == 1){
            noAdicionado = new No<>(objeto, null, this.inicio);
            this.inicio.anterior = noAdicionado;
            this.inicio = noAdicionado;

        } else if (posicao == this.tamanho) {
            noAdicionado = new No<>(objeto, this.fim.anterior, this.fim);
            this.fim.anterior.proximo = noAdicionado;
            this.fim.anterior = noAdicionado;

        } else {
            int pos = 1;
            No<T> noNaPosicao = this.inicio;

            while (pos++ < posicao){
                noNaPosicao = noNaPosicao.proximo;
            }

            noAdicionado = new No<>(objeto, noNaPosicao.anterior, noNaPosicao);
            noNaPosicao.anterior.proximo = noAdicionado;
            noNaPosicao.anterior = noAdicionado;
        }

        this.tamanho++;
    }

    public T remover() {
        if (this.estaVazia()){
            return null;
        }

        No<T> noRemovido = this.fim;

        noRemovido.anterior.proximo = null;
        this.fim = noRemovido.anterior;
        this.tamanho--;

        return noRemovido.getObjeto();
    }

    public T remover(int posicao) throws IllegalArgumentException {
        if (posicao < 1 || posicao > this.tamanho){
            throw new IllegalArgumentException("Valor fora do permitido.");
        }
        No<T> noRemovido;

        if (posicao == 1){
            noRemovido = this.inicio;
            if (noRemovido.proximo != null)
                noRemovido.proximo.anterior = null;
            this.inicio = noRemovido.proximo;

        } else if (posicao == this.tamanho) {
            noRemovido = this.fim;
            if (noRemovido.anterior != null)
                noRemovido.anterior.proximo = null;
            this.fim = noRemovido.anterior;

        } else {
            int pos = 1;
            No<T> noNaPosicao = this.inicio;

            while (pos++ < posicao){
                noNaPosicao = noNaPosicao.proximo;
            }

            noRemovido = noNaPosicao;
            if (noRemovido.anterior != null)
                noRemovido.anterior.proximo = noRemovido.proximo;
            if (noRemovido.proximo != null)
                noRemovido.proximo.anterior = noRemovido.anterior;
        }

        this.tamanho--;
        return noRemovido.getObjeto();
    }

}
