import java.util.Comparator;

public class DogSorter {
    public static Dog[] sort(SortingAlgorithm algoritm, Comparator<Dog> comparator, Dog... dogs) {
        switch (algoritm) {
            case SortingAlgorithm.BUBBLE_SORT:
                sortByBubble(comparator, dogs);
                break;
            case SortingAlgorithm.QUICK_SORT:
                sortByQuick(comparator, 0, dogs.length-1, dogs);
                break;
            default:
                break;
        }
        return dogs;
    }

    private static void sortByBubble(Comparator<Dog> comparator, Dog... dogs) {
        int n = dogs.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (0 < comparator.compare(dogs[j], dogs[j + 1])) {
                    swapArray(dogs, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    private static void sortByQuick(Comparator<Dog> comparator, int low, int high, Dog... dogs) {
        if (low < high) {
            
            int pivotIndex = partition(comparator, dogs, low, high);

            // recursion calls
            sortByQuick(comparator, low, pivotIndex - 1, dogs);
            sortByQuick(comparator, pivotIndex + 1, high, dogs);
        }
    }

    private static int partition(Comparator<Dog> comparator, Dog[] dogs, int low, int high) {    
        Dog pivot = dogs[high];
        
        int smallerIndex = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (0 > comparator.compare(dogs[j], pivot)) {
                smallerIndex++;
                swapArray(dogs, smallerIndex, j);
            }
        }
        swapArray(dogs, smallerIndex + 1, high);  
        return smallerIndex + 1;
    }

    private static void swapArray(Dog[] dogs, int i, int j) {
        Dog temp = dogs[i];
        dogs[i] = dogs[j];
        dogs[j] = temp;
    }
}
