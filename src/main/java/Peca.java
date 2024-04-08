public class Peca {

    private int valorA;
    private int valorB;
    private String cor;

    public Peca(int valorA, int valorB) {
        this.valorA = valorA;
        this.valorB = valorB;
        this.cor = ConsoleColor.ANSI_RESET;
    }

    public int getValorA() {
        return valorA;
    }

    public int getValorB() {
        return valorB;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void girar() {
        int temp = this.valorA;
        this.valorA = this.valorB;
        this.valorB = temp;
    }

    @Override
    public String toString() {
        return String.format("%s[%d|%d]%s", this.cor, this.valorA, this.valorB, ConsoleColor.ANSI_RESET);
    }
}
