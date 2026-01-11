import java.util.Scanner;

public class InputReader {
    private static boolean isInstantiated = false;

    private Scanner scanner;

    public InputReader() {
        if (isInstantiated) {
            throw new IllegalStateException("fel, endast en InpuReader får finnas");
        }
        isInstantiated = true;
        this.scanner = new Scanner(System.in);
    }

    public InputReader(Scanner s) {
        if (isInstantiated) {
            throw new IllegalStateException("fel, endast en InpuReader får finnas");
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

            } catch (Exception e) {
                System.out.println("fel input!");
            }
            if (i < 0) {
                System.out.println("fel input!");
            } else {
                done = true;
            }
        }

        // System.out.println("?>");
        scanner.nextLine();
        return i;
    }

    public double readDouble(String prompString) {
        boolean done = false;
        double d = -1;
        while (!done) {
            System.out.print(prompString + "?> ");

            try {
                d = scanner.nextDouble();

            } catch (Exception e) {
                System.out.println("fel input!");
            }
            if (d < 0) {
                System.out.println("fel input!");
            } else {
                done = true;
            }
        }

        scanner.nextLine();
        return d;
    }

    public String readString(String prompString) {

        System.out.print(prompString + "?> ");

        String s = scanner.nextLine().trim();
        return s;
    }
}
