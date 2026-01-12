
import java.util.Arrays;

public class DogRegister {
    private static boolean shouldClose = false;

    private static InputReader input = new InputReader();
    private static OwnerCollection owners = new OwnerCollection();

    public static void main(String[] args) {
        init();
        while (!shouldClose) {
            update();
        }
    }

    private static void init() {
        System.out.println("Welcome to the dog register!");
        helpPopup();
    }

    private static void update() {
        String command = input.readString("Enter command").toUpperCase();

        switch (command) {
            case "ADD OWNER":
                addOwner();
                break;
            case "REMOVE OWNER":
                removeOwner();
                break;
            case "ADD DOG":
                addDog();
                break;
            case "REMOVE DOG":
                removeDog();
                break;
            case "CHANGE OWNER":
                changeOwner();
                break;
            case "LIST OWNERS":
                listOwners();
                break;
            case "LIST DOGS":
                listDogs();
                break;
            case "INCREASE AGE":
                increaseAge();
                break;
            case "HELP":
                helpPopup();
                break;
            case "LOAD":
                loadDebugData();
                break;
            case "EXIT":
                System.out.println("Exiting program...");
                shouldClose = true;
                return;
            default:
                System.out.println("Error: Not a valid command. (Use 'help')");
        }
    }

    private static void addOwner() {

        String name = input.readString("Enter name");
        name = DogRegister.normalize(name);

        if (!owners.containsOwner(name)) {
            Owner newOwner = new Owner(name);
            owners.addOwner(newOwner);
            System.out.println(name + " has been added");
        } else {
            System.out.println("Fel: owner already exist!");
        }
    }

    private static void removeOwner() {
        if (!ownersExist())
            return;

        String name = input.readString("Enter name");

        if (owners.removeOwner(name)) {
            System.out.println(name + " was removed");
        } else {
            System.out.println("Error: " + name + " doesn't exist");
        }
    }

    private static void addDog() {
        if (!ownersExist())
            return;

        String ownerName = input.readString("Enter owner");
        ownerName = DogRegister.normalize(ownerName);

        if (owners.containsOwner(ownerName)) {
            String name = input.readString("Enter name");
            name = DogRegister.normalize(name);

            String breed = input.readString("Enter breed");
            breed = DogRegister.normalize(breed);

            int age = input.readInt("Enter age");
            double weight = input.readDouble("Enter weight");

            Dog newDog = new Dog(name, breed, age, weight, owners.getOwner(ownerName));
            owners.getOwner(ownerName).addDog(newDog);
        } else {
            System.out.println("Error: " + ownerName + " doesn't exist");
        }
    }

    private static void removeDog() {
        if (!ownersExist())
            return;

        String ownerName = input.readString("Enter owner");
        ownerName = DogRegister.normalize(ownerName);
        if (owners.containsOwner(ownerName)) {
            String name = input.readString("Enter dog");
            name = DogRegister.normalize(name);
            if (owners.getOwner(ownerName).ownsDog(name)) {
                owners.getOwner(ownerName).removeDog(name);
                System.out.println(name + " was removed");
            } else {
                System.out.println("Error: " + name + " doesn't exist");
            }
        } else {
            System.out.println("Error: " + ownerName + " doesn't exist");
        }
    }

    private static void changeOwner() {
        if (!ownersExist())
            return;

        String oldOwner = input.readString("Enter current owner");
        oldOwner = DogRegister.normalize(oldOwner);
        if (owners.containsOwner(oldOwner)) {

            String name = input.readString("Enter dog");
            name = DogRegister.normalize(name);
            if (owners.getOwner(oldOwner).ownsDog(name)) {

                String newOwner = input.readString("Enter new owner");
                newOwner = DogRegister.normalize(newOwner);
                if (owners.containsOwner(newOwner)) {

                    Dog dog = owners.getOwner(oldOwner).getDog(name);
                    owners.getOwner(oldOwner).removeDog(dog);
                    owners.getOwner(newOwner).addDog(dog);
                } else {
                    System.out.println("Error: " + newOwner + " doesn't exist");
                }
            } else {
                System.out.println("Error: " + name + " doesn't exist");
            }
        } else {
            System.out.println("Error: " + oldOwner + " doesn't exist");
        }
    }

    private static void listOwners() {
        if (!ownersExist())
            return;

        Owner[] ownerArray = new Owner[owners.getAllOwners().size()];
        ownerArray = owners.getAllOwners().toArray(ownerArray);
        int ownerPadding = 0;
        int dogPadding = 0;

        for (Owner owner : ownerArray) {
            ownerPadding = Math.max(ownerPadding, owner.getName().length());
            int dogPaddingCandidate = 0;
            for (int i = 0; i < owner.getDogs().length; i++) {
                dogPaddingCandidate += owner.getDog(i).getName().length() + 2; // 2 is padding for (, )
            }
            if (dogPaddingCandidate > dogPadding) // if this rows padding is the biggest
            {
                dogPadding = dogPaddingCandidate;
            }
        }

        System.out.println("-".repeat(ownerPadding + dogPadding));
        System.out.printf("%-" + ownerPadding + "s  ", "Name:");
        System.out.printf("Owns:\n");
        System.out.println("-".repeat(ownerPadding + dogPadding));

        for (int i = 0; i < ownerArray.length; i++) {
            System.out.printf("%-" + ownerPadding + "s  ", ownerArray[i].getName());

            for (int j = 0; j < ownerArray[i].getDogs().length; j++) {
                System.out.printf(ownerArray[i].getDogs()[j].getName());
                if (j != ownerArray[i].getDogs().length - 1) {
                    System.out.printf(", ");
                }
            }
            System.out.printf("\n");
        }
        System.out.println("-".repeat(ownerPadding + dogPadding) + "\n");
    }

    private static void listDogs() {
        if (!ownersExist())
            return;

        Dog[] dogs = owners.getAllDogs();
        double minLength = input.readDouble("Enter minimum tail length");
        DogSorter.sort(SortingAlgorithm.QUICK_SORT, new TailNameComparator(), dogs);

        if (minLength > 0) { // skip if unnessesery
            int passingDogs = 0;

            for (Dog dog : dogs) { // get amount of dog with correct length
                if (dog.getTailLength() >= minLength) {
                    passingDogs++;
                }
            }
            dogs = Arrays.copyOfRange(dogs,
                    dogs.length - passingDogs,
                    dogs.length);
        }

        int namePadding = 6;  // char length "Name: "
        int breedPadding = 7; // char length "Breed: "
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

    private static void increaseAge() {
        if (!ownersExist())
            return;

        Dog[] dogs = owners.getAllDogs();

        for (Dog dog : dogs) {
            dog.updateAge(1);
        }
    }

    private static void helpPopup() {
        System.out.println("The following commands are available:");
        System.out.println("* Add owner");
        System.out.println("* Remove owner");
        System.out.println("* Add dog");
        System.out.println("* Remove dog");
        System.out.println("* Change owner");
        System.out.println("* List owners");
        System.out.println("* List dogs");
        System.out.println("* Update age");
        System.out.println("* Help");
        System.out.println("* Exit");
    }

    // input command "load" to use
    private static void loadDebugData() {
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

        owners.addOwner(allOwners[0]);
        owners.addOwner(allOwners[1]);
        owners.addOwner(allOwners[2]);
        owners.addOwner(allOwners[3]);

        System.out.println("Testdata loaded!");
    }

    private static boolean ownersExist() {
        if (owners.size() == 0) {
            System.out.println("Error: no owners exist in registry");
            return false;
        }
        return true;
    }

    // * jag valde att inte skapa en utils.java eftersom det är ett mindre projekt
    // normalize(exAmPLE) -> Example
    public static String normalize(String s) {
        s = s.trim().toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

}
