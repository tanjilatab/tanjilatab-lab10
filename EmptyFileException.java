// Extend Java’s IOException class to be this EmptyFileException class. An exception of this type should be raised when the contents of the
// file to be parsed are empty. You should pass in a string to its constructor, which passes that string to the parent’s constructor
import java.util.*;

public class EmptyFileException extends IOException {
    public EmptyFileException(String path) {
        super(path + " was empty");
    }

}