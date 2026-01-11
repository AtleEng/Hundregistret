import java.util.Comparator;

public class SortByBreed implements Comparator<Dog>{
    public int compare(Dog a, Dog b) {
        String s1 = a.getBreed();
        String s2 = b.getBreed();
        if(s1 == s2)
        {
            return 0;
        }
         int minLength = Math.min(s1.length(), s2.length());

        for (int i = 0; i < minLength; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                return c1 - c2; // negative, zero, or positive
            }
        }
        // shorter string comes first in sort
        return s1.length() - s2.length();
        }   
}
