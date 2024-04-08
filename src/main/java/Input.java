import java.util.Scanner;

public class Input {

    Scanner scanner;

    public Input(){
        this.scanner = new Scanner(System.in);
    }

    public int obterInteiro(){
        return Integer.parseInt(this.scanner.nextLine());
    }

}
