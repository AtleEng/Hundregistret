
import java.util.Arrays;
import java.util.Comparator;

public class Owner {
    private static final int MAX_DOGS = 7;
    private final String name;

    private Dog[] dogs;
    private int numberOfDogs = 0;

    public Owner(String name, Dog... dogs) {
        this.name = DogRegister.normalize(name);
        this.dogs = new Dog[MAX_DOGS];

        for (int i = 0; i < dogs.length; i++) {
            addDog(dogs[i]);
        }
    }

    public String getName() {
        return name;
    }

    public boolean ownsAnyDog() {
        if (numberOfDogs == 0) {
            return false;
        }
        return true;
    }

    public boolean ownsMaxDogs() {
        if (numberOfDogs < MAX_DOGS) {
            return false;
        }
        return true;
    }

    public boolean ownsDog(Dog dog) {
        for (Dog ownerDog : dogs) {
            if (dog == ownerDog) {
                return true;
            }
        }
        return false;
    }
    public boolean ownsDog(String name) {
        name = DogRegister.normalize(name);
        for (Dog dog : dogs) {
            if (dog != null && dog.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // get owners dogs in a array
    public Dog[] getDogs() {
        Dog[] newDogs = new Dog[numberOfDogs];
        int addedDogs = 0;
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] != null) {
                newDogs[addedDogs] = dogs[i];
                addedDogs++;
            }
        }

        Arrays.sort(newDogs, Comparator.comparing(Dog::getName));
        return newDogs;
    }

    // get a dog from owner or return null
    public Dog getDog(int index) {
        if (index < 0 || index >= numberOfDogs) {
            return null;
        }
        return dogs[index];
    }
    public Dog getDog(String name) {
        name = DogRegister.normalize(name);
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }
        return null;
    }

    // add a dog to owner
    public boolean addDog(Dog dog) {
        if (dog == null || numberOfDogs >= dogs.length) {
            return false;
        }

        for (int i = 0; i < numberOfDogs; i++) {
            if (dogs[i].getName().equals(dog.getName())) {
                return false;
            }
        }

        dogs[numberOfDogs++] = dog;
        dog.setOwner(this);

        return true;
    }

    // remove a dog from owner
    public boolean removeDog(String name) {
        if (name == null)
            return false;

        name = DogRegister.normalize(name);

        for (int i = 0; i < numberOfDogs; i++) {
            if (dogs[i].getName().equals(name)) {
                return removeDog(dogs[i]);
            }
        }
        return false;
    }
    public boolean removeDog(Dog dog) {
        if (dog == null)
            return false;

        for (int i = 0; i < numberOfDogs; i++) {
            if (dogs[i] == dog) {

                for (int j = i; j < numberOfDogs - 1; j++) {
                    dogs[j] = dogs[j + 1];
                }

                dogs[--numberOfDogs] = null;
                dog.setOwner(null);

                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = name;
        for (Dog dog : dogs) {
            if (dog != null) {
                s += " " + dog.getName();
            }
        }
        return s;
    }
}