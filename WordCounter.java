import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.io.File;

public class WordCounter {

    public static int processText (StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        int wordcount = 0;
        Pattern regex = Pattern.compile("\\b\\w+\\b");
        Matcher regexMatcher = regex.matcher(text);
        boolean wordFound = false;

        // If the stopword is null, your method will count all words in the file. The methods returns the integer word count, unless the count was less than five, it which case it raises a TooSmallText exception
        if (stopword == null) {
            while (regexMatcher.find()) {
                wordcount++;
            }
            if (wordcount < 5) {
                throw new TooSmallText(wordcount);
            } else {
                return wordcount;
            }
        }

        int actualcount = 0;
        while (regexMatcher.find()) {
            wordcount++;                
            if (regexMatcher.group().toString().equals(stopword)) {
                wordFound = true;
                actualcount = wordcount;
            }
        }   

        if (wordcount < 5) {
            throw new TooSmallText(wordcount);
        } 
        else {
            if (wordFound == false) {
                throw new InvalidStopwordException(stopword);            
            } 
            else {
                return actualcount;
            }
        }
    }

    public static StringBuffer processFile (String path) throws EmptyFileException, IOException {
        StringBuffer buffer = new StringBuffer();
        File file = new File (path);
        while (file.exists() != true) {
            Scanner files = new Scanner(System.in);
            System.out.println("File could not be opened. Enter a valid file name: ");
            String newFile = files.nextLine();
            path = newFile;     
            file = new File (path);        
            files.close();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = reader.readLine();
        if (line == null) {
            reader.close();
            System.out.println("Warning! File empty.");
            throw new EmptyFileException(path);
        }
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        reader.close();
        return buffer;
    }

    public static void main (String[] args) {
        Scanner choose = new Scanner(System.in);
        System.out.println("Enter '1' to process a file, or '2' to process a text: ");
        String userinput = choose.nextLine();
        boolean validInput = false;
        if ((userinput.equals("1")) || (userinput.equals("2"))) {
            validInput = true;
        }

        // If the user enters an invalid option, allow them to choose again until they have a correct option
        while (validInput != true) {
            Scanner choose2 = new Scanner(System.in);
            System.out.println("Enter '1' to process a file, or '2' to process a text: ");
            String userinput2 = choose2.nextLine();
            if ((userinput2.equals("1")) || (userinput2.equals("2"))) {
                validInput = true;
                choose2.close();
            }
        }

        if (userinput.equals("1")) {
            String path = new String (args[0]);
            try {
                StringBuffer text = processFile(path);
                try {
                    String stopword = null; 
                    if (args.length >1 ) {
                        stopword = args[1];
                    }
                    int wordCount = processText(text,stopword);
                    System.out.println("Found " + wordCount + " words."); 
                } 
                catch (TooSmallText e) {
                    System.out.println(e);
                } 
                catch(InvalidStopwordException e) {
                }
            } 
            catch (EmptyFileException e) {
                System.out.println(e);
            } 
            catch (IOException e) {
            }
        }

        else if (userinput.equals("2")) { 
            StringBuffer text = new StringBuffer (args[0]);
            String stopword = new String (args[1]);
            boolean wordFound = false;
            boolean secondtry = false;
            int wordcount = 0;

            while (wordFound == false) {
                try {
                    wordcount = processText(text, stopword);
                    System.out.println(wordcount); 
                    wordFound = true;
                    if (stopword == null) {
                        System.out.println("No stopword was provided. The total number of words in the text is: " + wordcount);
                    } 
                    if (wordcount >=5) {
                        System.out.println("A valid stopword was provided. The number of words is: " + wordcount);
                    } 
                    if (wordcount < 5) {
                        System.out.println("WARNING! Text is too short.");
                    }
                } 
                catch (TooSmallText e) {
                } 
                catch (InvalidStopwordException e) {
                    if (secondtry != true) {
                        Scanner word = new Scanner(System.in);
                        System.out.println("Stopword not found. Enter a new topword: ");
                        String newword = word.nextLine();
                        stopword = newword;
                        secondtry = true;
                        word.close();
                    } else {
                        System.out.println("Sorry! Stopword not found.");
                    }
                }
            }
        }
        choose.close();
    }
}