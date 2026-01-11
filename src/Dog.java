public class Dog {
    // konstanter:
    private static final double DACHSHUND_TAIL_LENGTH = 3.7f;
    // statiska variabler:

    // instansvariabler:
    private final String name;
    private final String breed;
    private final double weight; // kg

    private int age;

    private Owner owner;

    // konstruktorer

    // Use for copy
    public Dog(Dog other) {
        this.name = other.name;
        this.breed = other.breed;
        this.age = other.age;
        this.weight = other.weight;
        this.owner = other.owner;
    }

    // Use for new creation
    public Dog(String name, String breed, int age, float weight) {
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

    // metoder:
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

    public double getTailLength() // in cm
    {
        String s = breed.toUpperCase();
        if ("TAX".equals(s) || "DACHSHUND".equals(s)) {
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
        if (value > 0) // förhindra overflow
        {
            age += value;
            if (age < 0) // TODO fix this shitcode (need unsigned int)
            {
                age = Integer.MAX_VALUE;
            }
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