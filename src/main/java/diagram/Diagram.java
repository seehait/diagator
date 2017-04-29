package diagram;

import java.util.ArrayList;

/**
 * Created by seehait on 4/29/17.
 */
public class Diagram {
    private static final int SIBLINGS_BETWEEN_ROOT = 0;
    private static final int SIBLINGS_FROM_PARENT = 1;
    private static final int SIBLINGS_FROM_CHILD = 2;

    private ArrayList<Node> roots;
    private Node currentNode;
    private Node previousNode;
    private ArrayList<Node> siblings;
    private int state;
    private int currentSiblingIndex;

    public Diagram() {
        roots = new ArrayList<Node>();

        state = SIBLINGS_BETWEEN_ROOT;
        currentSiblingIndex = 0;
    }

    public boolean addRootNode(Node root) {
        if (roots.contains(root)) {
            return false;
        }

        roots.add(root);
        if (currentNode == null) {
            currentNode = root;
        }
        return true;
    }

    public ArrayList<Node> getRoots() {
        return roots;
    }

    public boolean toChild() {
        if (currentNode.getChilds().size() > 0) {
            previousNode = currentNode;
            currentNode = currentNode.getChilds().get(0);
            state = SIBLINGS_FROM_PARENT;
            siblings = currentNode.getSiblingsFromParent(previousNode);
            currentSiblingIndex = 0;
            return true;
        }

        return false;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void toNextSibling() {
        currentSiblingIndex++;

        switch (state) {
            case SIBLINGS_BETWEEN_ROOT:
                if (currentSiblingIndex == roots.size()) {
                    currentSiblingIndex = 0;
                }
                currentNode = roots.get(currentSiblingIndex);
                break;

            case SIBLINGS_FROM_PARENT:
                if (currentSiblingIndex == siblings.size()) {
                    currentSiblingIndex = 0;
                }
                currentNode = siblings.get(currentSiblingIndex);
                break;

            case SIBLINGS_FROM_CHILD:
                if (currentSiblingIndex == siblings.size()) {
                    currentSiblingIndex = 0;
                }
                currentNode = siblings.get(currentSiblingIndex);
                break;

            default:
                break;
        }
    }
}
