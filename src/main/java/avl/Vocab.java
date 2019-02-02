package avl;

import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

    public static void main(String[] args) {
        //TODO: no argument case
        for (int i = 0; i < args.length; i++) {
            try {
                Scanner sc = new Scanner(new File(args[i]));
                Count c = wordCount(sc);
                System.out.println(c);
            } catch (FileNotFoundException exc) {
                System.out.println("Could not find file " + args[i]);
            }
        }
    }

    /* count the total and unique words in a document being read
     * by the given Scanner. return the two values in a Count object.*/
    private static Count wordCount(Scanner sc) {
        Count c = new Count();
        HashSet<String> set = new HashSet<>();

        while(sc.hasNext()) {
            String word = sc.next();
            word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            set.add(word);
            c.total += 1;
        }

        c.unique = set.size();
        return c;
    }

}
