/*
Driver: allows user to provide text via the terminal, a file, or directly to a method
1. a method called processText that expects a StringBuffer as argument (the text), and a String stopword, and counts the number of words in that text through that stopword
2. a method called processFile that expects a String path as an argument, and converts the contents of the file to a StringBuffer, which it returns.
3. a main method that asks the user to choose to process a file with option 1, or text with option 2
*/
import java.util.*;

public class WordCounter {

    public static int processtext(StringBuffer text, String stopword) throws InvalidStopwordException {
        int wordcount = 0;
        Pattern regex = Pattern.compile("your regular expression here");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            System.out.println("I just found the word: " + regexMatcher.group());
        } 
    }

    public static StringBuffer processFile (String path) {

    }

    public static void main (String[] args) {

    }

}