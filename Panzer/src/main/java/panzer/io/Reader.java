package panzer.io;

import panzer.contracts.InputReader;

import java.util.Scanner;

public class Reader implements InputReader {
    private Scanner scanner;

    public Reader(Scanner scanner) {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
