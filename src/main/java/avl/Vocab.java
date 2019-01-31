package avl;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

    public static void main(String[] args) {

        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            Count c = wordCount(sc);
            System.out.println();
            System.out.println(c);
        }

        for (String fn : args) {
            try {
                File f = new File(fn);
                Scanner sc = new Scanner(f);
                Count c = wordCount(sc);
                System.out.print(fn + " ");
                System.out.println(c);
            } catch (FileNotFoundException exc) {
                System.out.println("Could not find file " + args[0]);
            }
        }
    }

    /* count the total and unique words in a document being read
     * by the given Scanner. return the two values in a Count object.*/
    private static Count wordCount(Scanner sc) {
        return null;
    }

}
