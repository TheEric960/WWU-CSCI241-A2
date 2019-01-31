package avl;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

    public static void main(String[] args) {
        //TODO: no argument case
        try{
            Scanner sc = new Scanner(new File(args[0]));
            Count c = wordCount(sc);
            System.out.println(c);
        }catch(FileNotFoundException exc){
            System.out.println("Could not find file " +args[0]);
        }
    
    }

    /* count the total and unique words in a document being read
     * by the given Scanner. return the two values in a Count object.*/
    private static Count wordCount(Scanner sc) {
        Count c = new Count();
        while(sc.hasNext()){
            String word = sc.next();
            word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            // TODO: fill in the unique and total fields of c before c is returned
            //Hint: Something that stores only unique elements to tally # of uniques
        }
        return c;
    }

}
