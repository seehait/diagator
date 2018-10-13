package com.seehait.project.diagator.exceptions;

import com.seehait.project.diagator.diagram.Node;

/**
 * ChildNotExistedException
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class ChildNotExistedException extends RuntimeException {
    private Node child;

    /**
     * Constructs an instance.
     *
     * @param child child node
     */
    public ChildNotExistedException(Node child) {
        this.child = child;
    }

    @Override
    public String getMessage() {
        return String.format("Child %1s not existed.", child.getLabel());
    }
}
