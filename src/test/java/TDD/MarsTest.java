package TDD;

import TDD.MarsRover.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class MarsTest {
    @Test
    public void test_areaInfo(){
        AreaInfo areaInfo=new AreaInfo(5,3);
        assertEquals(5,areaInfo.getLength());
        assertEquals(3,areaInfo.getWidth());
    }
    @Test
    public void test_basic() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(3,2),new Direction("E"));
        assertEquals(5,marsCar.getAreaInfo().getLength());
        assertEquals(3,marsCar.getAreaInfo().getWidth());
        assertEquals(2,marsCar.getPoint().getY());
        assertEquals(3,marsCar.getPoint().getX());
        assertEquals("E",marsCar.getDirection().getCurDirection());
    }
    @Test
    public void test_FowardAndBack() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(3,2),new Direction("E"));
        MarsCommand commandmove=new MarsCommand("ffbbb");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("E",result.get("Direction"));
        assertEquals(2,result.get("curx"));
        assertEquals(2,result.get("cury"));
    }
    @Test
    public void test_Turn() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(3,2),new Direction("N"));
        MarsCommand commandmove=new MarsCommand("lrllr");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("W",result.get("Direction"));
        assertEquals(3,result.get("curx"));
        assertEquals(2,result.get("cury"));
    }


    public void test_Obstacle() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(0,1),new Direction("N"));
        MarsCommand commandmove=new MarsCommand("ff");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("N",result.get("Direction"));
        assertEquals(0,result.get("curx"));
        assertEquals(1,result.get("cury"));
    }
    public void test_Obstacle_err() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(0,0),new Direction("E"));
        MarsCommand commandmove=new MarsCommand("ffllrrffbb");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("N",result.get("Direction"));
        assertEquals(0,result.get("curx"));
        assertEquals(1,result.get("cury"));
    }

    @Test
    public void test_Obstacle_nomarl() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(0,0),new Direction("E"));
        MarsCommand commandmove=new MarsCommand("flrlffrfff");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("E",result.get("Direction"));
        assertEquals(4,result.get("curx"));
        assertEquals(2,result.get("cury"));
    }

    public void test_Obstacle_normal_more() throws Exception {
        MarsCar marsCar =new MarsCar(new AreaInfo(5,3),new Point(0,0),new Direction("E"));
        MarsCommand commandmove=new MarsCommand("flrlffrfffrfflflf");
        Map<Object,Object> result=marsCar.execCommand(commandmove);
        assertEquals("N",result.get("Direction"));
        assertEquals(0,result.get("curx"));
        assertEquals(0,result.get("cury"));
    }
//    public static void main(String[] args) throws Exception {
//        //1.探索区域信息
//        AreaInfo areaInfo=new AreaInfo(5,3);
//
//
//        //2.初始化信息：火星车的降落地点（x, y）和朝向（N, S, E, W）信息
//        Point point=new Point(0,0);
//        Direction direction=new Direction("E");
//        MarsCar marsCar =new MarsCar(areaInfo,point,direction);
//
//
//        //3.移动指令：火星车可以前进（f）或后退（b）
//        //4.转向指令：火星车可以左转90度（l）或右转90度（r）
//        MarsCommand commandmove=new MarsCommand("blfflffrflffr");
//        Map<Object,Object> result=marsCar.execCommand(commandmove);
//        System.out.println(result.toString());
//
//
//    }



}
