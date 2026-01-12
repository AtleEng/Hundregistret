
import java.util.ArrayList;
import java.util.Comparator;

public class OwnerCollection {

    private ArrayList<Owner> owners = new ArrayList<Owner>();

    public OwnerCollection(){}

    public boolean addOwner(Owner owner) {
        if (owner != null) {
            owners.add(owner);
            return true;
        }
        return false;
    }

    public boolean removeOwner(String ownerName) {
        ownerName = DogRegister.normalize(ownerName);
        for (Owner owner : owners) {

            if (owner.getName().equals(ownerName)) {
                owners.remove(owner);
                return true;
            }
        }
        return false;
    }

    public boolean removeOwner(Owner owner) {
        if(owners.contains(owner))
        {
            owners.remove(owner);
            return true;
        }
        
        return false;
    }

    public boolean containsOwner(String ownerName) {
        if(owners.size() <= 0)
        {
            return false;
        }
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getName().equals(ownerName)) {
                return true;
            }
        }
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
                return owners.get(i);
            }
        }
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