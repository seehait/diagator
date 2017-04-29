package exceptions;

import diagram.Node;

/**
 * Created by seehait on 4/29/17.
 */
public class ChildNotExistedException extends RuntimeException {
    private Node child;

    public ChildNotExistedException(Node child) {
        this.child = child;
    }

    @Override
    public String getMessage() {
        return String.format("Child %1s not existed.", child.getLabel());
    }
}
