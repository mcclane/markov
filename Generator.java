import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.util.ArrayList;

public class Generator {

    private HashMap<String, HashSet<String>> words;
    private ArrayList<String> text;

    public Generator(String fileName) {
        text = new ArrayList<String>();
        //read the fileName passed in
        try {
            Scanner sc = new Scanner(new File(fileName));
            //read in the file
            while(sc.hasNext()) {
                text.add(sc.next());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void generateMap(int prefixSize) {
        //generate prefix/suffix frequency map from text
        words = new HashMap<String, HashSet<String>>();
        int len = text.size();
        for(int i = 0;i < text.size()-prefixSize-1;i++) {
            String prefix = "";
            for(int j = i;j < i+prefixSize;j++)
                prefix += text.get(j)+" ";
            prefix = prefix.trim();
            String suffix = text.get(i+prefixSize).trim();
            //check if the suffix is already in the map
            HashSet<String> internal;
            if(words.containsKey(prefix)) {
                internal = words.get(prefix);
            }
            else {
                internal = new HashSet<String>();
            }
            internal.add(suffix);
            words.put(prefix, internal);
        }
    }
    public String generateText(String seed, int length) {
        String prefix = seed;
        String generatedText = prefix+" ";
        for(int i = 0;i < length;i++) {
            //find a random word from the internal suffix list
            HashSet<String> internal = words.get(prefix);
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
            generatedText+= suffix+" ";
            prefix = shift(prefix, suffix);
        }
        return generatedText;
    }
    public String shift(String original, String next) {
        String[] splitted = original.split(" ");
        String shifted = "";
        for(int i = 1;i < splitted.length;i++) {
            shifted += " "+splitted[i];
        }
        shifted += " "+next;
        return shifted.trim();
    }
}
