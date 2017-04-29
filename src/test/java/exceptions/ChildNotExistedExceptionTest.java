package exceptions;

import diagram.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by seehait on 4/29/17.
 */
public class ChildNotExistedExceptionTest {
    ChildNotExistedException underTest;

    @Before
    public void setUp() {
        underTest = new ChildNotExistedException(new Node("Child"));
    }

    @Test
    public void messageShouldMatch() {
        Assert.assertEquals("Child Child not existed.", underTest.getMessage());
    }
}
