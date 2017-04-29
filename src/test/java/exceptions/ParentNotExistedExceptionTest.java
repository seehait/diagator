package exceptions;

import diagram.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by seehait on 4/29/17.
 */
public class ParentNotExistedExceptionTest {
    ParentNotExistedException underTest;

    @Before
    public void setUp() {
        underTest = new ParentNotExistedException(new Node("Parent"));
    }

    @Test
    public void messageShouldMatch() {
        Assert.assertEquals("Parent Parent not existed.", underTest.getMessage());
    }
}
