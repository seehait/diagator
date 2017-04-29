package exceptions;

import diagram.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ChildNotExistedExceptionTest
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
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
