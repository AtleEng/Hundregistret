import java.util.Comparator;

public class SortByAge implements Comparator<Dog>{
    public int compare(Dog a, Dog b) {
            return a.getAge() - b.getAge();
        }
}
