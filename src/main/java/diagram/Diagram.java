package diagram;

import java.util.ArrayList;

import utilities.AudioPlayer;

/**
 * Diagram
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class Diagram {
    private static final int SIBLINGS_BETWEEN_ROOT = 0;
    private static final int SIBLINGS_FROM_PARENT = 1;
    private static final int SIBLINGS_FROM_CHILD = 2;

    private ArrayList<Node> siblings;
    private ArrayList<Node> roots;
    private Node currentNode;
    private Node previousNode;
    private int currentSiblingIndex;
    private int previousState;
    private int state;

    /**
     * Constructs an instance.
     */
    public Diagram() {
        roots = new ArrayList<>();

        state = SIBLINGS_BETWEEN_ROOT;
        currentSiblingIndex = 0;
    }

    /**
     * Adds root node.
     *
     * @param root root node to be added
     *
     * @return success
     */
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

    /**
     * Gets root node.
     *
     * @return root node
     */
    public ArrayList<Node> getRoots() {
        return roots;
    }

    /**
     * Gets current node.
     *
     * @return current node
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * Moves to parent node.
     *
     * @return success
     */
    public boolean toParent() {
        if (currentNode.getParents().size() > 0) {
            previousNode = currentNode;
            currentNode = currentNode.getParents().get(0);
            state = SIBLINGS_FROM_CHILD;
            siblings = currentNode.getSiblingsFromChild(previousNode);
            currentSiblingIndex = 0;
            return true;
        }

        return false;
    }

    /**
     * Moves to child node.
     *
     * @return success
     */
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

    /**
     * Moves to next siblings node.
     *
     * @return success
     */
    public void toNextSiblings() {
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

    /**
     * Moves to previous siblings node.
     *
     * @return success
     */
    public void toPreviousSiblings() {
        currentSiblingIndex--;

        switch (state) {
            case SIBLINGS_BETWEEN_ROOT:
                if (currentSiblingIndex == -1) {
                    currentSiblingIndex = roots.size() - 1;
                }
                currentNode = roots.get(currentSiblingIndex);
                break;

            case SIBLINGS_FROM_PARENT:
                if (currentSiblingIndex == -1) {
                    currentSiblingIndex = siblings.size() - 1;
                }
                currentNode = siblings.get(currentSiblingIndex);
                break;

            case SIBLINGS_FROM_CHILD:
                if (currentSiblingIndex == -1) {
                    currentSiblingIndex = siblings.size() - 1;
                }
                currentNode = siblings.get(currentSiblingIndex);
                break;

            default:
                break;
        }
    }

    /**
     * Toggles to root siblings switching mode.
     */
    public void toggleRootSiblingsMode() {
        if (state == SIBLINGS_BETWEEN_ROOT) {
            state = previousState;
        } else if (roots.contains(currentNode)) {
            previousState = state;
            state = SIBLINGS_BETWEEN_ROOT;
        }
    }

    /**
     * Plays current node's label.
     */
    public void playLabel() {
        AudioPlayer.getAudioPlayer().play(currentNode.getLabel());
    }

    /**
     * Plays current node's information.
     */
    public void playInfo() {
        /*
            Generate appropriate information string then use AudioPlayer to play.
         */
    }
}
