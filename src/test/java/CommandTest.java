import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CommandTest {
    @Test
    public void test_has_value(){
        Command command=new Command("-l true -d /usr/local");
        assertEquals(command.getValue("l"),"true");
        assertEquals(command.getValue("d"),"/usr/local");
    }

    @Test
    public void test_has_no_value(){
        Command command=new Command("-l -d /usr/local");
        assertNull(command.getValue("l"));
        assertEquals(command.getValue("d"),"/usr/local");
    }

    @Test
    public void test_has_no_negative(){
        Command command=new Command("-l -p -9 -d /usr/local");
        assertNull(command.getValue("l"));
        assertEquals(command.getValue("p"),"-9");
        assertEquals(command.getValue("d"),"/usr/local");
    }
}
