public class Peca {

    private int valorA;
    private int valorB;

    public Peca(int valorA, int valorB) {
        this.valorA = valorA;
        this.valorB = valorB;
    }

    public int getValorA() {
        return valorA;
    }

    public int getValorB() {
        return valorB;
    }

    public void girar() {
        int temp = this.valorA;
        this.valorA = this.valorB;
        this.valorB = temp;
    }

    @Override
    public String toString() {
        return String.format("[%d|%d]", this.valorA, this.valorB);
    }
}
