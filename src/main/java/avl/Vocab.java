/*
Author: Jack Wilson
Date: 2/6/2019
Purpose: Practice creating BST and AVL trees

To include the fourth enhancement, I added another if statement for that
specific case. I also added a duplicate Count class and HashSet for easier
counting. I moved the file read-in to a new method to avoid duplicating
code and to retain original functionality of the original purpose.
 */

package avl;

import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

    private static int windowSize = 30;

    public static void main(String[] args) {
        // no args entered else scan file
        if (args.length == 0) {
            Count c = wordCount(new Scanner(System.in));
            System.out.println(c);
        } else if (args.length == 1) {
            findWordCountFromFile(args[0]);
        } else {
            // read every file
            for (String f : args) {
                findWordCountFromFile(f);
            }
        }
    }

    /* count the total and unique words in a document being read
     * by the given Scanner. return the two values in a Count object.*/
    private static Count wordCount(Scanner sc) {
        Count c = new Count();
        Count c2 = new Count();
        HashSet<String> set = new HashSet<>();  // doesn't allow duplicates
        HashSet<String> set2 = new HashSet<>();

        while(sc.hasNext()) {
            String word = sc.next();
            word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            set.add(word);
            set2.add(word);
            c.total += 1;
            c2.total++;
            if (c2.total % windowSize == 0) {
                c2.unique = set2.size();
                System.out.println(c2);
                set2 = new HashSet<>();
                c2.total = 0;
            }
        }
        c2.unique = set2.size();
        System.out.println(c2);

        c.unique = set.size();
        return c;
    }

    private static void findWordCountFromFile(String f) {
        try {
            Scanner sc = new Scanner(new File(f));
            Count c = wordCount(sc);
            System.out.println("Grand total: " + c);
        } catch (FileNotFoundException exc) {
            System.out.println("Could not find file " + f);
        }
    }

}
