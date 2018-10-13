package com.seehait.project.diagator.diagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.seehait.project.diagator.exceptions.ChildNotExistedException;
import com.seehait.project.diagator.exceptions.ParentNotExistedException;

/**
 * Node
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class Node {
    private String label;
    private ArrayList<Node> parents;
    private ArrayList<Node> childs;
    private HashMap<Node, ArrayList<Node>> siblingsFromParent;
    private HashMap<Node, ArrayList<Node>> siblingsFromChild;

    /**
     * Constructs an instance.
     *
     * @param label label
     */
    public Node(String label) {
        this.label = label;
        parents = new ArrayList<Node>();
        childs = new ArrayList<Node>();
        siblingsFromParent = new HashMap<Node, ArrayList<Node>>();
        siblingsFromChild = new HashMap<Node, ArrayList<Node>>();
    }

    /**
     * Gets label
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Adds parent node.
     *
     * @param parent parent
     * @return success
     */
    public boolean addParent(Node parent) {
        if (parents.contains(parent)) {
            return false;
        }

        parents.add(parent);
        parent.addChild(this);
        siblingsFromParent.put(parent, new ArrayList<Node>());
        siblingsFromParent.get(parent).add(this);
        return true;
    }

    /**
     * Gets parent nodes.
     *
     * @return parent nodes
     */
    public ArrayList<Node> getParents() {
        return parents;
    }

    /**
     * Adds child node.
     *
     * @param child child
     * @return success
     */
    public boolean addChild(Node child) {
        if (childs.contains(child)) {
            return false;
        }

        childs.add(child);
        child.addParent(this);
        siblingsFromChild.put(child, new ArrayList<Node>());
        siblingsFromChild.get(child).add(this);
        return true;
    }

    /**
     * Gets child nodes.
     *
     * @return child nodes
     */
    public ArrayList<Node> getChilds() {
        return childs;
    }

    /**
     * Adds siblings node from parent.
     *
     * @param siblings siblings
     * @return success
     */
    public boolean addSiblingsFromParent(Node parent, Node siblings) {
        if (parents.contains(parent) && !siblingsFromParent.get(parent).contains(siblings)) {
            siblingsFromParent.get(parent).add(siblings);
            siblings.addSiblingsFromParent(parent, this);
            return true;
        }

        return false;
    }

    /**
     * Gets siblings nodes from parent.
     *
     * @return siblings nodes
     */
    public ArrayList<Node> getSiblingsFromParent(Node parent) {
        if (parents.contains(parent)) {
            return siblingsFromParent.get(parent);
        }

        throw new ParentNotExistedException(parent);
    }

    /**
     * Adds siblings node from child.
     *
     * @param siblings siblings
     * @return success
     */
    public boolean addSiblingsFromChild(Node child, Node siblings) {
        if (childs.contains(child) && !siblingsFromChild.get(child).contains(siblings)) {
            siblingsFromChild.get(child).add(siblings);
            siblings.addSiblingsFromChild(child, this);
            return true;
        }

        return false;
    }

    /**
     * Gets siblings nodes from child.
     *
     * @return siblings nodes
     */
    public ArrayList<Node> getSiblingsFromChild(Node child) {
        if (childs.contains(child)) {
            return siblingsFromChild.get(child);
        }

        throw new ChildNotExistedException(child);
    }
}
