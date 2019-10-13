package TDD;

import TDD.Args;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ArgTest {
    @Test
    public void test(){
        Args args=new Args("l:bool,d:str,p:int","-l true -d /usr/local -p 8080");
        assertEquals(args.getValue("l"),Boolean.TRUE);
        assertEquals(args.getValue("d"),"/usr/local");
        assertEquals(args.getValue("p"),8080);
    }

    @Test
    public void test_with_nuber(){
        Args args=new Args("l:bool,d:str,p:int","-l  -d /usr/local -p -9");
        assertEquals(args.getValue("l"),Boolean.FALSE);
        assertEquals(args.getValue("d"),"/usr/local");
        assertEquals(args.getValue("p"),-9);
    }
}
