package diagram;

import exceptions.ChildNotExistedException;
import exceptions.ParentNotExistedException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by seehait on 4/29/17.
 */
public class Node {
    private String label;
    private ArrayList<Node> parents;
    private ArrayList<Node> childs;
    private HashMap<Node, ArrayList<Node>> siblingsFromParent;
    private HashMap<Node, ArrayList<Node>> siblingsFromChild;

    public Node(String label) {
        this.label = label;
        parents = new ArrayList<Node>();
        childs = new ArrayList<Node>();
        siblingsFromParent = new HashMap<Node, ArrayList<Node>>();
        siblingsFromChild = new HashMap<Node, ArrayList<Node>>();
    }

    public String getLabel() {
        return label;
    }

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

    public ArrayList<Node> getParents() {
        return parents;
    }

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

    public ArrayList<Node> getChilds() {
        return childs;
    }

    public boolean addSiblingsFromParent(Node parent, Node siblings) {
        if (parents.contains(parent) && !siblingsFromParent.get(parent).contains(siblings)) {
            siblingsFromParent.get(parent).add(siblings);
            siblings.addSiblingsFromParent(parent, this);
            return true;
        }

        return false;
    }

    public ArrayList<Node> getSiblingsFromParent(Node parent) {
        if (parents.contains(parent)) {
            return siblingsFromParent.get(parent);
        }

        throw new ParentNotExistedException(parent);
    }

    public boolean addSiblingsFromChild(Node child, Node siblings) {
        if (childs.contains(child) && !siblingsFromChild.get(child).contains(siblings)) {
            siblingsFromChild.get(child).add(siblings);
            siblings.addSiblingsFromChild(child, this);
            return true;
        }

        return false;
    }

    public ArrayList<Node> getSiblingsFromChild(Node child) {
        if (childs.contains(child)) {
            return siblingsFromChild.get(child);
        }

        throw new ChildNotExistedException(child);
    }
}
