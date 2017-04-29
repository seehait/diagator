package diagram;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DiagramTest
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class DiagramTest {
    private Diagram underTest;
    private Node[] nodes;
    private Node[] underTestNodes;
    private static final String LABEL = "Label";

    @Before
    public void setUp() {
        underTest = new Diagram();

        nodes = new Node[5];
        for (int i = 0; i < 5; i++) {
            nodes[i] = new Node(LABEL);
        }

        setUnderTestNode();
    }

    private void setUnderTestNode() {
        underTestNodes = new Node[5];
        underTestNodes[0] = new Node("Root");
        for (int i = 1; i < 5; i++) {
            underTestNodes[i] = new Node(LABEL);
        }

        underTestNodes[0].addChild(underTestNodes[1]);
        underTestNodes[0].addChild(underTestNodes[2]);
        underTestNodes[0].addChild(underTestNodes[3]);
        underTestNodes[1].addChild(underTestNodes[4]);

        underTestNodes[1].addSiblingsFromParent(underTestNodes[0], underTestNodes[2]);
        underTestNodes[1].addSiblingsFromParent(underTestNodes[0], underTestNodes[3]);
    }

    @Test
    public void diagramShouldHasOneRoot() {
        underTest.addRootNode(nodes[0]);

        Assert.assertEquals(1, underTest.getRoots().size());
    }

    @Test
    public void diagramShouldHasTwoRoots() {
        underTest.addRootNode(nodes[0]);
        underTest.addRootNode(nodes[1]);

        Assert.assertEquals(2, underTest.getRoots().size());
    }

    @Test
    public void diagramShouldHasUniqueRoot() {
        underTest.addRootNode(nodes[0]);
        underTest.addRootNode(nodes[0]);

        Assert.assertEquals(1, underTest.getRoots().size());
    }

    @Test
    public void diagramShouldMoveToCorrectChild() {
        underTest.addRootNode(underTestNodes[0]);
        underTest.toChild();

        Assert.assertEquals(underTestNodes[1], underTest.getCurrentNode());
    }

    @Test
    public void diagramShouldMoveToCorrectSiblings() {
        underTest.addRootNode(underTestNodes[0]);
        underTest.toChild();

        underTest.toNextSiblings();
        Assert.assertEquals(underTestNodes[2], underTest.getCurrentNode());

        underTest.toNextSiblings();
        Assert.assertEquals(underTestNodes[3], underTest.getCurrentNode());

        underTest.toNextSiblings();
        Assert.assertEquals(underTestNodes[1], underTest.getCurrentNode());
    }
}
