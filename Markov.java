import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.util.ArrayList;

public class Markov {
    public static void main(String[] args) {
        Generator gen = new Generator("source.txt");
        gen.generateMap(2);

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("\nEnter in a seed:");
            String seed = input.nextLine().trim();
            System.out.println(gen.generateText(seed, 500));
            System.out.println();
        }

        /*HashMap<String, HashSet<String>> wc = new HashMap<String, HashSet<String>>();
        ArrayList<String> text = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File("source.txt"));
            //read in the file
            while(sc.hasNext()) {
                text.add(sc.next());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        //generate prefix/suffix frequency map
        int len = text.size();
        for(int i = 0;i < text.size()-2;i++) {
            String prefix = text.get(i)+" "+text.get(i+1);
            String suffix = text.get(i+2);
            //check if the suffix is already in the map
            HashSet<String> internal;
            if(wc.containsKey(prefix)) {
                internal = wc.get(prefix);
            }
            else {
                internal = new HashSet<String>();
            }
            internal.add(suffix);
            wc.put(prefix, internal);
        }
        //now that we have a frequency map, we can create a markov chain
        Scanner ip = new Scanner(System.in);
        int limit = 100; //words
        while(true) {
            System.out.println("Enter two seed words: ");
            String prefix = ip.nextLine().trim();
            System.out.print(prefix+" ");
            for(int i = 0;i < limit;i++) {
                //find a random word from the internal suffix
                HashSet<String> internal = wc.get(prefix);
                String suffix = "";
                int randIndex = (int)(Math.random()*internal.size());
                int randCounter = 0;
                for(String k : internal) {
                    if(randIndex == randCounter) {
                        suffix = k;
                        break;
                    }
                    randCounter++;
                }
                System.out.print(suffix+" ");
                prefix = prefix.split(" ")[1]+" "+suffix;
            }
        }*/
    }

}
