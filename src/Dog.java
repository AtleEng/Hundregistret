public class Dog {
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    private final String name;
    private final String breed;
    private final double weight;

    private int age;
    private Owner owner;

    // Use for new creation
    public Dog(String name, String breed, int age, double weight) {
        this.name = DogRegister.normalize(name);
        this.breed = DogRegister.normalize(breed);
        this.age = age;
        this.weight = weight;
    }

    public Dog(String name, String breed, int age, double weight, Owner owner) {
        this.name = DogRegister.normalize(name);
        this.breed = DogRegister.normalize(breed);
        this.age = age;
        this.weight = weight;
        setOwner(owner);
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getTailLength() {
        if (breed.equalsIgnoreCase("TAX") || breed.equalsIgnoreCase("DACHSHUND")) {
            return DACHSHUND_TAIL_LENGTH;
        }
        return (age * weight) / 10;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean setOwner(Owner owner) {
        if (this.owner == owner) {
            return false;
        }
        if (this.owner != null) {
            this.owner.removeDog(this);
        }
        this.owner = owner;
        if (owner != null && !owner.ownsDog(this)) {
            owner.addDog(this);
        }
        return true;
    }

    public void updateAge(int value) {
        if (value <= 0)
            return;

        if (age > Integer.MAX_VALUE - value) {
            age = Integer.MAX_VALUE;
        } else {
            age += value;
        }
    }

    @Override
    public String toString() {
        String ownerName = "No owner";
        if (owner != null) {
            ownerName = owner.getName();
        }
        return String.format("%s %s %d %f %f %s", name, breed, age, weight, getTailLength(), ownerName);
    }
}