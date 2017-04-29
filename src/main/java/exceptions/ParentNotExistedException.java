package exceptions;

import diagram.Node;

import java.util.regex.PatternSyntaxException;

/**
 * ParentNotExistedException
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class ParentNotExistedException extends RuntimeException {
    private Node parent;

    /**
     * Constructs an instance.
     *
     * @param parent parent node
     */
    public ParentNotExistedException(Node parent) {
        this.parent = parent;
    }

    @Override
    public String getMessage() {
        return String.format("Parent %1s not existed.", parent.getLabel());
    }
}
