package exceptions;

import diagram.Node;

import java.util.regex.PatternSyntaxException;

/**
 * Created by seehait on 4/29/17.
 */
public class ParentNotExistedException extends RuntimeException {
    private Node parent;

    public ParentNotExistedException(Node parent) {
        this.parent = parent;
    }

    @Override
    public String getMessage() {
        return String.format("Parent %1s not existed.", parent.getLabel());
    }
}
