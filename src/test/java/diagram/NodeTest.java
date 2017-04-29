package diagram;

import exceptions.ChildNotExistedException;
import exceptions.ParentNotExistedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * NodeTest
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class NodeTest {
    private Node underTest;
    private Node[] nodes;
    private static final String LABEL = "Label";

    @Before
    public void setUp() {
        underTest = new Node(LABEL);

        nodes = new Node[5];
        for (int i = 0; i < 5; i++) {
            nodes[i] = new Node(LABEL);
        }
    }

    @Test
    public void nodeShouldHasCorrectLabel() {
        Assert.assertEquals(LABEL, underTest.getLabel());
    }

    @Test
    public void nodeShouldContainCorrectParent() {
        underTest.addParent(nodes[0]);

        Assert.assertEquals(nodes[0], underTest.getParents().get(0));
    }

    @Test
    public void nodeShouldHasCorrectNumberOfParents() {
        underTest.addParent(nodes[0]);
        underTest.addParent(nodes[1]);

        Assert.assertEquals(2, underTest.getParents().size());
    }

    @Test
    public void nodeShouldHasUniqueParent() {
        underTest.addParent(nodes[0]);
        underTest.addParent(nodes[0]);

        Assert.assertEquals(1, underTest.getParents().size());
    }

    @Test
    public void nodeShouldContainCorrectChild() {
        underTest.addChild(nodes[0]);

        Assert.assertEquals(nodes[0], underTest.getChilds().get(0));
    }

    @Test
    public void nodeShouldHasCorrectNumberOfChilds() {
        underTest.addChild(nodes[0]);
        underTest.addChild(nodes[1]);

        Assert.assertEquals(2, underTest.getChilds().size());
    }

    @Test
    public void setNodesShouldHasUniqueChild() {
        underTest.addChild(nodes[0]);
        underTest.addChild(nodes[0]);

        Assert.assertEquals(1, underTest.getChilds().size());
    }

    @Test
    public void nodeShouldBeAbleToAddSiblingsToExistedParent() {
        underTest.addParent(nodes[0]);

        Assert.assertTrue(underTest.addSiblingsFromParent(nodes[0], nodes[1]));
    }

    @Test
    public void nodeShouldNotBeAbleToAddSiblingsToNonExistedParent() {
        underTest.addParent(nodes[0]);

        Assert.assertFalse(underTest.addSiblingsFromParent(nodes[1], nodes[2]));
    }

    @Test
    public void nodeShouldReturnCorrectSiblingsFromParent() {
        underTest.addParent(nodes[0]);
        underTest.addSiblingsFromParent(nodes[0], nodes[1]);

        underTest.addParent(nodes[2]);
        underTest.addSiblingsFromParent(nodes[2], nodes[3]);

        Assert.assertEquals(nodes[1], underTest.getSiblingsFromParent(nodes[0]).get(1));
        Assert.assertEquals(nodes[3], underTest.getSiblingsFromParent(nodes[2]).get(1));
    }

    @Test(expected = ParentNotExistedException.class)
    public void nodeShouldThrowExceptionWhenGetSiblingsFromNotExistedParent() {
        underTest.getSiblingsFromParent(nodes[0]);
    }

    @Test
    public void NodeShouldHasUniqueSiblingsFromParent() {
        underTest.addParent(nodes[0]);
        underTest.addSiblingsFromParent(nodes[0], nodes[1]);
        underTest.addSiblingsFromParent(nodes[0], nodes[1]);

        Assert.assertEquals(2, underTest.getSiblingsFromParent(nodes[0]).size());
    }

    @Test
    public void nodeShouldBeAbleToAddSiblingsToExistedChild() {
        underTest.addChild(nodes[0]);

        Assert.assertTrue(underTest.addSiblingsFromChild(nodes[0], nodes[1]));
    }

    @Test
    public void nodeShouldNotBeAbleToAddSiblingsToNonExistedChild() {
        underTest.addChild(nodes[0]);

        Assert.assertFalse(underTest.addSiblingsFromChild(nodes[1], nodes[2]));
    }

    @Test
    public void nodeShouldReturnCorrectSiblingsFromChild() {
        underTest.addChild(nodes[0]);
        underTest.addSiblingsFromChild(nodes[0], nodes[1]);

        underTest.addChild(nodes[2]);
        underTest.addSiblingsFromChild(nodes[2], nodes[3]);

        Assert.assertEquals(nodes[1], underTest.getSiblingsFromChild(nodes[0]).get(1));
        Assert.assertEquals(nodes[3], underTest.getSiblingsFromChild(nodes[2]).get(1));
    }

    @Test(expected = ChildNotExistedException.class)
    public void nodeShouldThrowExceptionWhenGetSiblingsFromNotExistedChild() {
        underTest.getSiblingsFromChild(nodes[0]);
    }

    @Test
    public void NodeShouldHasUniqueSiblingsFromChild() {
        underTest.addChild(nodes[0]);
        underTest.addSiblingsFromChild(nodes[0], nodes[1]);
        underTest.addSiblingsFromChild(nodes[0], nodes[1]);

        Assert.assertEquals(2, underTest.getSiblingsFromChild(nodes[0]).size());
    }

    @Test
    public void nodeShouldAddParentBack() {
        underTest.addParent(nodes[0]);

        Assert.assertEquals(underTest, nodes[0].getChilds().get(0));
    }

    @Test
    public void nodeShouldAddChildBack() {
        underTest.addChild(nodes[0]);

        Assert.assertEquals(underTest, nodes[0].getParents().get(0));
    }

    @Test
    public void nodeShouldAddSiblingsFromParentBack() {
        underTest.addParent(nodes[0]);
        nodes[1].addParent(nodes[0]);
        underTest.addSiblingsFromParent(nodes[0], nodes[1]);

        Assert.assertEquals(underTest, nodes[1].getSiblingsFromParent(nodes[0]).get(1));
    }

    @Test
    public void nodeShouldAddSiblingsFromChildBack() {
        underTest.addChild(nodes[0]);
        nodes[1].addChild(nodes[0]);
        underTest.addSiblingsFromChild(nodes[0], nodes[1]);

        Assert.assertEquals(underTest, nodes[1].getSiblingsFromChild(nodes[0]).get(1));
    }
}
