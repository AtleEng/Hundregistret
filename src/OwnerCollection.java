
import java.util.ArrayList;
import java.util.Comparator;

public class OwnerCollection {
    // konstanter:

    // statiska variabler:

    // instansvariabler:

    private ArrayList<Owner> owners = new ArrayList<Owner>();

    // konstruktorer
    public OwnerCollection()
    {

    }
    // metoder:

    public boolean addOwner(Owner owner) {
        if (owner != null) {
            owners.add(owner);
            System.out.println("Adding owner: "+ owner.getName());
            return true;
        }
        System.out.println("Owner is null!");
        return false;
    }

    public boolean removeOwner(String ownerName) {
        ownerName = DogRegister.normalize(ownerName);
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

    public boolean removeOwner(Owner owner) {
        return removeOwner(owner.getName());
    }

    public boolean containsOwner(String ownerName) {
        if(owners.size() <= 0)
        {
            return false;
        }
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getName().equals(ownerName)) {
                System.out.println("Found owner: " + ownerName);
                return true;
            }
        }
        System.out.println("Collection has no owner named:" + ownerName);
        return false;
    }

    public boolean containsOwner(Owner owner) {
        return containsOwner(owner.getName());
    }

    public Owner getOwner(String ownerName)
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

    public ArrayList<Owner> getAllOwners()
    {
        owners.sort(Comparator.comparing(Owner::getName, String.CASE_INSENSITIVE_ORDER));
        return owners;
    }

    public Dog[] getAllDogs()
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

    public int size()
    {
        return owners.size();
    }

    @Override
    public String toString() {
        return String.format("OwnerCollection: (%d)", size());
    }
}