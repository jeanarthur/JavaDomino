package domino;

import estruturaDeDados.Lista;

public class Jogador {

    private String nome;
    private Lista<Peca> pecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pecas = new Lista<>();
    }

    public String getNome() {
        return nome;
    }

    public Lista<Peca> getPecas() {
        return pecas;
    }
}
