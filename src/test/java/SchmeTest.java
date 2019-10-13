import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchmeTest {

    @Test
    public void test_bool(){
        Schmes schmes=new Schmes("l:bool");
        assertEquals(schmes.getValue("l","true"),Boolean.TRUE);
        assertEquals(schmes.getValue("l","false"),Boolean.FALSE);
        assertEquals(schmes.getValue("l",null),Boolean.FALSE);
    }

    @Test
    public void test_int(){
        Schmes schmes=new Schmes("l:int");
        assertEquals(schmes.getValue("l","1"),new Integer("1"));

    }

    @Test
    public void test_String(){
        Schmes schmes=new Schmes("l:str");
        assertEquals(schmes.getValue("l","hello"),"hello");

    }
}
