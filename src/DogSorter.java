import java.util.Comparator;

public class DogSorter {
    public static Dog[] Sort(SortingAlgorithm algoritm, Comparator<Dog> comparator, Dog... dogs) {

        switch (algoritm) {
            case BUBBLE_SORT: SortByBubble(comparator, dogs); break;
        
            default:
                break;
        }
        return dogs;
    }

    static void SortByBubble(Comparator<Dog> comparator, Dog... dogs)
    {
        int nDogs = dogs.length;
        boolean swapped;

        for (int i = 0; i < nDogs - 1; i++)
        {
            swapped = false;
            for (int j = 0; j < nDogs - i - 1; j++)
            {
                if(1 < comparator.compare(dogs[j], dogs[j + 1]))
                {
                    //Swap elements
                    Dog temp = dogs[j + 1];
                    dogs[j + 1] = dogs[j];
                    dogs[j] = temp;
                    swapped = true;
                }
            }

            if(!swapped) {break;}
        }
    }

    class SortByAge implements Comparator<Dog> {

        public int compare(Dog a, Dog b) {
            return a.getAge() - b.getAge();
        }
    }
        public static enum SortingAlgorithm {
            BUBBLE_SORT,
            INSERTION_SORT,
            SELECTION_SORT
        }
}
