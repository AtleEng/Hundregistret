
import java.util.Arrays;

public class Owner
{
    // konstanter:

    // statiska variabler:

    // instansvariabler:
    private final String  name;

    private Dog[] dogs;

    // konstruktorer
    public Owner(String name, Dog... dogs)
    {
        this.name = App.normalize(name);
        this.dogs = new Dog[dogs.length];

        for (int i = 0; i < dogs.length && i < 7; i++) {
            this.dogs[i] = new Dog(dogs[i]);
            this.dogs[i].SetOwner(this);
        }
    }

    // metoder:
    public String getName()
    {
        return name;
    }

    //get a copy of owners dogs
    public Dog[] getDogs()
    {
        return Arrays.copyOf(dogs, dogs.length);
    }

    //get the pointer to a dog
    public Dog getDog(int index)
    {
        return dogs[index];
    }
    public Dog getDog(String name)
    {
        for (Dog dog : dogs) 
        {
            if (dog.getName().equals(name))
            {
                System.out.println("Found dog: " + name);
                return dog;
            }
        }
        System.out.println(this.name + " has no dog named:" + name);
        return null;
    }

    // add a dog to owner
    public boolean addDog(Dog dog)
    {
        if(dogs.length >= 7)
        {
            System.out.println("Owner has too many dogs (max 7), not adding: " + dog.getName());
            return false;
        }

        dogs = Arrays.copyOf(dogs, dogs.length + 1);
        dogs[dogs.length - 1] = new Dog(dog);
        dogs[dogs.length - 1].SetOwner(this);

        System.out.println("Adding: " + dog.getName() + " to owner: " + name);
        return true;
    }

    // remove a dog from owner
    public boolean removeDog(String name)
    {
        for(int i = 0; i < dogs.length; i++)
        {
            if(dogs[i].getName().equals(name))
            {
                System.out.println("Removed dog: " + name);
                dogs[i] = dogs[dogs.length - 1];
                dogs = Arrays.copyOf(dogs, dogs.length - 1);
                return true;
            }
        }
        System.out.println(this.name + " has no dog named:" + name);
        return false;
    }

    public boolean removeDog(Dog dog)
    {
        return removeDog(dog.getName());
    }



    @Override
    public String toString() {
        return String.format(name);
    }
}