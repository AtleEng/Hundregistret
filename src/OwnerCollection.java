
import java.util.ArrayList;

public class OwnerCollection {
    // konstanter:

    // statiska variabler:

    // instansvariabler:

    private static ArrayList<Owner> owners = new ArrayList<Owner>();

    // konstruktorer

    // metoder:

    public static boolean addOwner(Owner owner) {
        if (owner != null) {
            owners.add(owner);
            return true;
        }
        System.out.println("Owner is null!");
        return false;
    }

    public static boolean removeOwner(String ownerName) {
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getName().equals(ownerName)) {
                owners.remove(owners.get(i));
                System.out.println("Removed owner: " + ownerName);
                return true;
            }
        }
        System.out.println("Collection has no owner named:" + ownerName);
        return false;
    }

    public static boolean removeOwner(Owner owner) {
        return removeOwner(owner.getName());
    }

    public static boolean containsOwner(String ownerName) {
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getName().equals(ownerName)) {
                System.out.println("Found owner: " + ownerName);
                return true;
            }
        }
        System.out.println("Collection has no owner named:" + ownerName);
        return false;
    }

    public static boolean containsOwner(Owner owner) {
        return containsOwner(owner.getName());
    }

    public static Owner getOwner(String ownerName)
    {
        for(int i = 0; i < owners.size(); i++)
        {
            if(owners.get(i).getName().equals(ownerName))
            {
                System.out.println("Found owner: " + ownerName);
                return owners.get(i);
            }
        }
        System.out.println("Collection has no owner named:" + ownerName);
        return null;
    }

    public static ArrayList<Owner> getOwners()
    {
        return owners;
    }

    public static Dog[] getAllDogs()
    {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        for (int i = 0; i < owners.size(); i++) 
        {
            for (int j = 0; j < owners.get(i).getDogs().length; j++) {
                
                dogs.add(owners.get(i).getDog(j));
            }
        }
        return dogs.toArray(new Dog[dogs.size()]);
    }

    public static int size()
    {
        return owners.size();
    }

    @Override
    public String toString() {
        return String.format("OwnerCollection: (%d)", size());
    }
}