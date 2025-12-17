
//import java.util.Scanner;

import java.util.Scanner;

public class App {
    static boolean shouldClose = false;

    static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);

        System.out.println("Welcome to the dog register!");
        LoadDebugData();
        ListOwners();
        ListDogs();
        HelpPopup();
        while (!shouldClose) {

            System.out.print("Enter command?> ");
            String command = input.nextLine().trim().toUpperCase();

            switch (command) {
                case "AO":
                    AddOwner();
                    break;
                case "RO":
                    RemoveOwner();
                    break;
                case "AD":
                    AddDog();
                    break;
                case "RD":
                    RemoveDog();
                    break;
                case "CO":
                    ChangeOwner();
                    break;
                case "LO":
                    ListOwners();
                    break;
                case "LD":
                    ListDogs();
                    break;
                case "UA":
                    UpdateAge();
                    break;
                case "HELP":
                    HelpPopup();
                    break;
                case "LOAD":
                    LoadDebugData();
                    break;
                case "EXIT":
                    System.out.println("Exiting program...");
                    shouldClose = true;
                    return;

                default:
                    System.out.println("Not a valid command.");
            }
        }
    }

    static void AddOwner() {

        System.out.print("Enter name?> ");
        String name = input.nextLine();

        Owner newOwner = new Owner(name);
        OwnerCollection.addOwner(newOwner);
        System.out.println("Owner added");
    }

    static void RemoveOwner() {
        System.out.println("[RemoveOwner] Not implemented yet");
    }

    static void AddDog() {
        System.out.println("[AddDog] Not implemented yet");
    }

    static void RemoveDog() {
        System.out.println("[RemoveDog] Not implemented yet");
    }

    static void ChangeOwner() {
        System.out.println("[ChangeOwner] Not implemented yet");
    }

    static void ListOwners() {
        Owner[] owners = new Owner[OwnerCollection.getOwners().size()];
        owners = OwnerCollection.getOwners().toArray(owners);
        int ownerPadding = 0;
        int dogPadding = 0;

        for (Owner owner : owners) {
            ownerPadding = Math.max(ownerPadding, owner.getName().length());
            int dogPaddingCandidate = 0;
            for (int i = 0; i < owner.getDogs().length; i++) 
            {
                dogPaddingCandidate += owner.getDog(i).getName().length() + 2; // 2 is padding for (, )
            }
            if(dogPaddingCandidate > dogPadding) // if this rows padding is the biggest
            {
                dogPadding = dogPaddingCandidate;
            }
        }

        System.out.println("-".repeat(ownerPadding + dogPadding));
        System.out.printf("%-" + ownerPadding + "s  ", "Name:");
        System.out.printf("Owner:\n");
        System.out.println("-".repeat(ownerPadding + dogPadding));

        for (int i = 0; i < owners.length; i++) {
            System.out.printf("%-" + ownerPadding + "s  ", owners[i].getName());

            for (int j = 0; j < owners[i].getDogs().length; j++) {
                System.out.printf(owners[i].getDogs()[j].getName());
                if(j != owners[i].getDogs().length - 1)
                {
                    System.out.printf(", ");
                }
            }
            System.out.printf("\n");
        }
        System.out.println("-".repeat(ownerPadding + dogPadding) + "\n");
       }

    // returns the longest word in a array of strings (use for formating)
    static int getMaxString(String[] strings) {
        int max = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > max) {
                max = strings[i].length();
            }
        }
        return max;
    }

    static void ListDogs() {
        Dog[] dogs = OwnerCollection.getAllDogs();
        int namePadding = 0;
        int breedPadding = 0;

        for (Dog dog : dogs) {
            namePadding = Math.max(namePadding, dog.getName().length());
            breedPadding = Math.max(breedPadding, dog.getBreed().length());
        }

        System.out.println("-".repeat(namePadding + breedPadding + 5 + 7 + 5 + 2 * 8));
        System.out.printf("%-" + namePadding + "s  ", "Name:");
        System.out.printf("%-" + breedPadding + "s  ", "Breed:");
        System.out.printf("%-" + 4 + "s  ", "Age:");
        System.out.printf("%-" + 7 + "s  ", "Weight:");
        System.out.printf("%-" + 5 + "s  ", "Tail:");
        System.out.printf("Owner:\n");
        System.out.println("-".repeat(namePadding + breedPadding + 5 + 7 + 5 + 2 * 8));

        for (int i = 0; i < dogs.length; i++) {
            System.out.printf("%-" + namePadding + "s  ", dogs[i].getName());
            System.out.printf("%-" + breedPadding + "s  ", dogs[i].getBreed());
            System.out.printf("%-" + 4 + "s  ", dogs[i].getAge());
            String weighString = String.format("%.2f", dogs[i].getWeight());
            System.out.printf("%-" + 7 + "s  ", weighString);
            String tailString = String.format("%.2f", dogs[i].getTailLength());
            System.out.printf("%-" + 5 + "s  ", tailString);
            System.out.println(dogs[i].getOwner().getName());
        }
        System.out.println("-".repeat(namePadding + breedPadding + 5 + 7 + 5 + 2 * 8) + "\n");
    }

    static void UpdateAge() {
        System.out.println("[UpdateAge] Not implemented yet");
    }

    static void HelpPopup() {
        System.out.println("The following commands are available:");
        System.out.println("* Add owner     (AO)");
        System.out.println("* Remove owner  (RO)");
        System.out.println("* Add dog       (AD)");
        System.out.println("* Remove dog    (RD)");
        System.out.println("* Change owner  (CO)");
        System.out.println("* List owners   (LO)");
        System.out.println("* List dogs     (LD)");
        System.out.println("* Update age    (UA)");
        System.out.println("* Help");
        System.out.println("* Exit");
        System.out.println("* Load (Debug)");
    }

    static void LoadDebugData() {
        Dog[] allDogs = new Dog[8];
        allDogs[0] = new Dog("Alfa", "Beagle", 3, 4.8f);
        allDogs[1] = new Dog("Bravo", "tax", 2, 3.6f);
        allDogs[2] = new Dog("Charlie", "pudel", 4, 4.9f);
        allDogs[3] = new Dog("Delta", "terrier", 2, 3.7f);
        allDogs[4] = new Dog("Echo", "terrier", 1, 3.3f);
        allDogs[5] = new Dog("Foxtrot", "cocker spaniel", 1, 5.4f);
        allDogs[6] = new Dog("Golf", "tax", 4, 3.4f);
        allDogs[7] = new Dog("Hotel", "beagle", 5, 5.4f);

        Owner[] allOwners = new Owner[4];
        allOwners[0] = new Owner("Adam", allDogs[0], allDogs[1], allDogs[2], allDogs[3]);
        allOwners[1] = new Owner("Bertil", allDogs[4]);
        allOwners[2] = new Owner("Cesar", allDogs[5]);
        allOwners[3] = new Owner("David", allDogs[6], allDogs[7]);

        OwnerCollection.addOwner(allOwners[0]);
        OwnerCollection.addOwner(allOwners[1]);
        OwnerCollection.addOwner(allOwners[2]);
        OwnerCollection.addOwner(allOwners[3]);
    }

    public static String normalize(String s) {
        s = s.trim().toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
