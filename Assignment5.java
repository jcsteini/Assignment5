/*
4/11/2017 CS-202-18371
This program creates a rudimentary spell-checking program using a 
BinarySearchTree container to find the average number of comparisons it took to 
find words in the dictionary and the average number of comparisons it performed 
when it could not find words in the dictionary.
 */
package assignment5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Steininger
 */
public class Assignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] count = new int[1];
        long wordsFound = 0;
        long wordsNotFound = 0;
        long compsFound = 0;
        long compsNotFound = 0;
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<String>();
        }

        try {
            File f = new File("random_dictionary.txt");
            Scanner input = new Scanner(f);
            String current;
            while (input.hasNext()) {
                current = input.next();
                current = current.toLowerCase();
                dictionary[current.charAt(0) - 97].insert(current);
            }//while
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File f = new File("oliver.txt");
            Scanner input = new Scanner(f);
            String[] current = new String[11];

            while (input.hasNext()) {
                current[0] = input.next();
                //System.out.println("First \t" + current[0]);
                current[0] = current[0].toLowerCase();
                if (current[0].contains("didn't")) {
                    current[0] = current[0].replaceAll("[^didn't]", " ");
                } else if (current[0].contains("can't")) {
                    current[0] = current[0].replaceAll("[^can't]", " ");
                } else if (current[0].contains("won't")) {
                    current[0] = current[0].replaceAll("[^won't]", " ");
                } else if (current[0].contains("wouldn't")) {
                    current[0] = current[0].replaceAll("[^wouldn't]", " ");
                } else if (current[0].contains("shouldn't")) {
                    current[0] = current[0].replaceAll("[^shouldn't]", " ");
                } else if (current[0].contains("mustn't")) {
                    current[0] = current[0].replaceAll("[^mustn't]", " ");
                } else if (current[0].contains("I'm")) {
                    current[0] = current[0].replaceAll("[^I'm]", " ");
                } else if (current[0].contains("couldn't")) {
                    current[0] = current[0].replaceAll("[^couldn't]", " ");
                } else if (current[0].contains("we'll")) {
                    current[0] = current[0].replaceAll("[^we'll]", " ");
                } else if (current[0].contains("you're")) {
                    current[0] = current[0].replaceAll("[^you're]", " ");
                } else if (current[0].contains("it'd")) {
                    current[0] = current[0].replaceAll("[^it'd]", " ");
                } else if (current[0].contains("i'll")) {
                    current[0] = current[0].replaceAll("[^i'll]", " ");
                } else if (current[0].contains("i'd")) {
                    current[0] = current[0].replaceAll("[^i'd]", " ");
                } else if (current[0].contains("isn't")) {
                    current[0] = current[0].replaceAll("[^isn't]", " ");
                } else if (current[0].contains("doesn't")) {
                    current[0] = current[0].replaceAll("[^doesn't]", " ");
                } else if (current[0].contains("wasn't")) {
                    current[0] = current[0].replaceAll("[^wasn't]", " ");
                } else if (current[0].contains("weren't")) {
                    current[0] = current[0].replaceAll("[^weren't]", " ");
                } else if (current[0].contains("we've")) {
                    current[0] = current[0].replaceAll("[^we've]", " ");
                } else if (current[0].contains("who'll")) {
                    current[0] = current[0].replaceAll("[^who'll]", " ");
                } else if (current[0].contains("you'll")) {
                    current[0] = current[0].replaceAll("[^you'll]", " ");
                } else if (current[0].contains("she'll")) {
                    current[0] = current[0].replaceAll("[^she'll]", " ");
                } else if (current[0].contains("he'll")) {
                    current[0] = current[0].replaceAll("[^he'll]", " ");
                } else if (current[0].contains("he'd")) {
                    current[0] = current[0].replaceAll("[^he'd]", " ");
                } else if (current[0].contains("you'd")) {
                    current[0] = current[0].replaceAll("[^you'd]", " ");
                } else if (current[0].contains("they'll")) {
                    current[0] = current[0].replaceAll("[^they'll]", " ");
                } else if (current[0].contains("they'd")) {
                    current[0] = current[0].replaceAll("[^they'd]", " ");
                } else if (current[0].contains("they're")) {
                    current[0] = current[0].replaceAll("[^they're]", " ");
                } else if (current[0].contains("could've")) {
                    current[0] = current[0].replaceAll("[^could've]", " ");
                } else if (current[0].contains("should've")) {
                    current[0] = current[0].replaceAll("[^should've]", " ");
                } else if (current[0].contains("would've")) {
                    current[0] = current[0].replaceAll("[^would've]", " ");
                } else {
                    current[0] = current[0].replaceAll("[^a-zA-Z]", " ");
                }

                //current[0] = current[0].toLowerCase();
                current[0] = current[0].trim();
                //System.out.println("Second \t" + current[0]);
                String[] tokens = current[0].split(" ");

                if (tokens.length == 2 && tokens[1].contentEquals("s")) {
                    //if (tokens[1].contentEquals("s")) {
                    tokens[1] = "";
                    // }
                }

                for (int i = 0; i < tokens.length; i++) {
                    if (!tokens[i].isEmpty()) {
                        if (dictionary[(int) tokens[i].charAt(0) - 'a'].search(tokens[i], count)) {
                            wordsFound++;
                            compsFound += count[0];
                        } else {
                            wordsNotFound++;
                            compsNotFound += count[0];
                        }
                    }
                }//for
            }//while
            input.close();
            System.out.println("Words Found: \t\t\t\t\t" + wordsFound);
            System.out.println("Words Not Found: \t\t\t\t" + wordsNotFound);
            System.out.println("Comparisons Found: \t\t\t\t" + compsFound);
            System.out.println("Comparisons Not Found: \t\t\t\t" + compsNotFound);
            double avgcwf = (double) compsFound / wordsFound;
            double avgcwnf = (double) compsNotFound / wordsNotFound;
            System.out.printf("Average Comparisons for Words Found: \t\t%.2f", avgcwf);
            System.out.printf("\nAverage Comparisons for Words Not Found: \t%.2f\n", avgcwnf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
/*
Words Found: 					940534
Words Not Found: 				53927
Comparisons Found: 				15371603
Comparisons Not Found: 				531947
Average Comparisons for Words Found: 		16.34
Average Comparisons for Words Not Found: 	9.86

Words Found: 					940565
Words Not Found: 				54049
Comparisons Found: 				15372205
Comparisons Not Found: 				533533
Average Comparisons for Words Found: 		16.34
Average Comparisons for Words Not Found: 	9.87

Words Found: 					940434
Words Not Found: 				54401
Comparisons Found: 				15371082
Comparisons Not Found: 				537450
Average Comparisons for Words Found: 		16.34
Average Comparisons for Words Not Found: 	9.88

Words Found: 					940434
Words Not Found: 				58625
Comparisons Found: 				15371082
Comparisons Not Found: 				567018
Average Comparisons for Words Found: 		16.34
Average Comparisons for Words Not Found: 	9.67
 */
 /*
original
Words Found: 					940258
Words Not Found: 				59283
Comparisons Found: 				15368952
Comparisons Not Found: 				573063
Average Comparisons for Words Found: 		16.35
Average Comparisons for Words Not Found: 	9.67
 */
