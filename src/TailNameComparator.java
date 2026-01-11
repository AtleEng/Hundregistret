import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog>{
    public int compare(Dog a, Dog b) {
           int value = (int)(a.getTailLength() * 1000 - b.getTailLength() * 1000);
        if(value == 0)
        {

            String s1 = a.getName();
            String s2 = b.getName();
            int minLength = Math.min(s1.length(), s2.length());
            
            for (int i = 0; i < minLength; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                
                if (c1 != c2) {
                    return c1 - c2; // negative, zero, or positive
                }
            }
            // shorter string comes first in sort
            value = s1.length() - s2.length();
        }
           return value;
        }
}