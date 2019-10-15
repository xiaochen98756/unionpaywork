package TDD;

import TDD.Schmes;
import org.junit.Test;

import java.util.*;

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
    public void test_multi(){
        Schmes schmes=new Schmes("l:str,t:str,w:strlist,k:strlist,o:map");
        assertEquals(schmes.getValue("l","hello"),"hello");
        assertEquals(schmes.getValue("t","world"),"world");
        assertEquals(schmes.getValue("w","qqqq,wwww,aaaa,eeee"),"[qqqq, wwww, aaaa, eeee]");
        assertEquals(schmes.getValue("k",null),String.valueOf(Boolean.FALSE));
        assertEquals(schmes.getValue("k",""),String.valueOf("[]"));
        assertEquals(schmes.getValue("o","{\"boolean\":true,\"string\":\"string\",\"list\":[1,2,3],\"int\":2}"),
                "{boolean=true, string=string, list=[1,2,3], int=2}");
        assertEquals(schmes.getValue("o","{}"),
                "{}");

    }
    @Test
    public void test_String(){
        Schmes schmes=new Schmes("l:str");
        assertEquals(schmes.getValue("l","hello"),"hello");

    }
}
