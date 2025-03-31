//Extend Java’s Exception class to be this exception that is raised when the stopword is not found in the text.
import java.util.*;

public class InvalidStopwordException extends Exception {
    public InvalidStopwordException(String stopword) {
        super("Couldn't find stopword: " + stopword);
    }

}