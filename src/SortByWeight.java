import java.util.Comparator;

public class SortByWeight implements Comparator<Dog>{
    public int compare(Dog a, Dog b) {
           return (int)(a.getWeight() * 1000 - b.getWeight() * 1000);
        }
}