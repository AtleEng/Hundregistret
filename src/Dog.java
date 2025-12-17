public class Dog
{
    // konstanter:
    static final float DACHSHUND_TAIL_LENGTH = 3.7f;
    // statiska variabler:

    // instansvariabler:
    private final String name;
    private final String breed;
    private final float  weight; //kg
    
    private int age;

    private Owner owner;

    // konstruktorer
    
    // Use for copy
    public Dog(Dog other) 
    {
        this.name = other.name;
        this.breed = other.breed;
        this.age = other.age;
        this.weight = other.weight;
        this.owner = other.owner;
    }
    // Use for new creation
    public Dog(String name, String breed, int age, float weight)
    {
        this.name =  App.normalize(name);
        this.breed = App.normalize(breed);
        this.age = age;
        this.weight = weight;
    }
    public Dog(String name, String breed, int age, float weight, Owner owner)
    {
        this.name =  App.normalize(name);
        this.breed = App.normalize(breed);
        this.age = age;
        this.weight = weight;
        SetOwner(owner);
    }

    // metoder:
    public String getName()
    {
        return name;
    }
    public String getBreed()
    {
        return breed;
    }
    public int getAge()
    {
        return age;
    }
    public float getWeight()
    {
        return weight;
    }
    public float getTailLength() // in cm
    {
        if("tax".equals(breed)) //TODO make it work with diffrent names
        {
            return DACHSHUND_TAIL_LENGTH;
        }
        return (age * weight) / 10;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public final boolean SetOwner(Owner owner)
    {
        if(this.owner == owner)
        {
            return false;
        }
        System.out.println(String.format("%s new owner is: %s", name, owner.getName()));
        this.owner = owner;
        return true;
    }

    public void updateAge(int newValue)
    {
        if(newValue < age)
        {
            System.out.print(String.format("Updated age of %s", name));
            return;
        }
        age = newValue;
    }

    @Override
    public String toString() {
        return String.format(name);
    }
}