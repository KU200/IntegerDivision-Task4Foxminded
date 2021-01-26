import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivisionServiceTest {

    private DivisionService subject;

    @Before
    public void initSubject() {
        subject = new Division();
    }

    @Test
    public void divide() {
		
		/* Given */
        String expected =
                "_256│8\n"  +
                " 24 │--\n" +
                " -- │32\n" +
                " _16\n"  +
                "  16\n" +
                "  --\n" +
                "   0\n";
		/* When */
        String actual = subject.divide(256, 8);

		/* Then */
        assertEquals(expected, actual);
    }
}
