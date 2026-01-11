
public class Owner {
    // konstanter:

    // statiska variabler:

    // instansvariabler:
    private final String name;

    private Dog[] dogs;
    private int numberOfDogs = 0;

    // konstruktorer
    public Owner(String name, Dog... dogs) {
        this.name = DogRegister.normalize(name);
        this.dogs = new Dog[7];

        for (int i = 0; i < dogs.length; i++) {
            addDog(dogs[i]);
        }
    }

    // metoder:
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
        if (numberOfDogs < 7) {
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
            if (dog == null) {
                return false;
            }
            if (dog.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // get a copy of owners dogs
    public Dog[] getDogs() {
        Dog[] newDogs = new Dog[numberOfDogs];
        int addedDogs = 0;
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] != null) {
                newDogs[addedDogs] = dogs[i];
                addedDogs++;
            }
        }
        return newDogs;
    }

    // get the pointer to a dog
    public Dog getDog(int index) {
        return dogs[index];
    }

    public Dog getDog(String name) {
        name = DogRegister.normalize(name);
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) {
                System.out.println("Found dog: " + name);
                return dog;
            }
        }
        System.out.println(this.name + " has no dog named:" + name);
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
                s += " ";
                s += dog.getName();
            }
        }
        return String.format(s);
    }
}