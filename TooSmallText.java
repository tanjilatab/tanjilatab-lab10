//Extend Java’s Exception class to be this exception that is raised when the length of the text is less than five words.
import java.util.*;

public class TooSmallText extends Exception  {
    public TooSmallText(int wordcount) {
        super("Only found " + wordcount + " words.");
    }
}