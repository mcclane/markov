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

    private HashMap<String, ArrayList<String>> words;
    private ArrayList<String> text;
    /**
     * Constructor. 
     * @param fileName name of file to be read in.
     */
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
    /**
    * Generates a prefix -> suffix map using the file read in from constructor.
    * @param prefixSize number of words in each prefix.
    */
    public void generateMap(int prefixSize) {
        //generate prefix/suffix frequency map from text
        words = new HashMap<String, ArrayList<String>>();
        int len = text.size();
        for(int i = 0;i < text.size()-prefixSize-1;i++) {
            String prefix = "";
            for(int j = i;j < i+prefixSize;j++)
                prefix += text.get(j)+" ";
            prefix = prefix.trim();
            String suffix = text.get(i+prefixSize).trim();
            //check if the suffix is already in the map
            ArrayList<String> internal;
            if(words.containsKey(prefix)) {
                internal = words.get(prefix);
            }
            else {
                internal = new ArrayList<String>();
            }
            internal.add(suffix);
            words.put(prefix, internal);
        }
    }
    /**
     * Generates text of given length starting with a given seed by creating a Markov chain.
     * @param   seed    starting text for the markov chain.
     * @param   length  number of words in the text generated.
     * @return          Text generated from seed and with given length.
     */
    public String generateText(String seed, int length) {
        String prefix = seed;
        String generatedText = prefix+" ";
        for(int i = 0;i < length;i++) {
            //find a random word from the internal suffix list
            int randIndex = (int)(Math.random()*words.get(prefix).size());
            String suffix = words.get(prefix).get(randIndex);
            generatedText += suffix+" ";
            prefix = shift(prefix, suffix);
        }
        return generatedText;
    }
    /**
     * Returns String passed in without first word and with next word appended.
     * @param   original    Original String that is to be shifted.
     * @param   next        String to be appended to the end of the original String.
     * @return              original string without first word and with next word appended.
     */
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
