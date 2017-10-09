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
        Generator gen = new Generator("sources/source.txt");
        gen.generateMap(5);

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("\nEnter in a seed:");
            String seed = input.nextLine().trim();
            System.out.println(gen.generateText(seed, 500));
            System.out.println();
        }
    }
}
