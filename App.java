
//import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);

        System.out.println("Hundregistret - Start");

        Dog dog1 = new Dog("Alfa",      "Beagle",           3, 4.8f);
        Dog dog2 = new Dog("Bravo",     "tax",              2, 3.6f);
        Dog dog3 = new Dog("Charlie",   "pudel",            4, 4.9f);
        Dog dog4 = new Dog("Delta",     "terrier",          2, 3.7f);
        Dog dog5 = new Dog("Foxtrot",   "cocker spaniel",   1, 5.4f);
        Owner owner1 = new Owner("Adam",    dog1, dog2);
        Owner owner2 = new Owner("Bertil",  dog3);
        Owner owner3 = new Owner("Cesar",   dog4, dog5);


        owner2.removeDog("Charlie");

        Dog dog6 = new Dog("Echo", "collie", 6, 8.4f);
        owner3.addDog(dog6);
        owner3.getDog("Echo").updateAge(200);
        dog6.updateAge(1000);

        System.out.println("=== List ==============================");
        System.out.println(owner1.toString());
        System.out.println(owner2.toString());
        System.out.println(owner3.toString());

        System.out.println("");
    }

    public static String normalize(String s) {
        s = s.trim().toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
