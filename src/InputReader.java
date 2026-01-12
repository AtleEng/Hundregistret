import java.util.Scanner;

public class InputReader {
    private static boolean isInstantiated = false;

    private Scanner scanner;

    public InputReader() {
        if (isInstantiated) {
            throw new IllegalStateException("Error: only one inputReader can exist");
        }
        isInstantiated = true;
        this.scanner = new Scanner(System.in);
    }

    public InputReader(Scanner s) {
        if (isInstantiated) {
            throw new IllegalStateException("Error: only one inputReader can exist");
        }
        isInstantiated = true;
        this.scanner = s;
    }

    public int readInt(String prompString) {
        boolean done = false;
        int i = 0;
        while (!done) {
            System.out.print(prompString + "?> ");

            try {
                i = scanner.nextInt();
                if (i < 0) {
                    System.out.println("Error: invalid input!");
                } else {
                    done = true;
                }

            } catch (Exception e) {
                System.out.println("Error: invalid input!");
            }
            scanner.nextLine();
        }
        return i;
    }

    public double readDouble(String prompString) {
        boolean done = false;
        double d = -1;
        while (!done) {
            System.out.print(prompString + "?> ");

            try {
                d = scanner.nextDouble();
                if (d < 0) {
                    System.out.println("Error: invalid input!");
                } else {
                    done = true;
                }

            } catch (Exception e) {
                System.out.println("Error: invalid input!");
            }
            scanner.nextLine();
        }
        return d;
    }

    public String readString(String prompString) {

        System.out.print(prompString + "?> ");

        String s = scanner.nextLine().trim();
        return s;
    }
}
