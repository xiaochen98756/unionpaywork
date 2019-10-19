package TDD.MarsRover;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Direction {
    //N, S, E, W
    public static String Direction_NORTH="N";
    public static String Direction_SOUTH="S";
    public static String Direction_EAST="E";
    public static String Direction_WEST="W";

    public  static List<String> DirectionList=new ArrayList<String>(){};


    private String curDirection="";

    public static Map<String,String> leftDirectionCommandMap=new HashMap<>();
    public static Map<String,String> rightDirectionCommandMap=new HashMap<>();

    public Direction(String direction) throws Exception {
        initalInputDirection(direction);
        initalDirectionMap();
    }

    private void initalDirectionMap() {
        leftDirectionCommandMap.put(Direction_SOUTH,Direction_EAST);
        leftDirectionCommandMap.put(Direction_EAST,Direction_NORTH);
        leftDirectionCommandMap.put(Direction_NORTH,Direction_WEST);
        leftDirectionCommandMap.put(Direction_WEST,Direction_SOUTH);

        rightDirectionCommandMap.put(Direction_SOUTH,Direction_WEST);
        rightDirectionCommandMap.put(Direction_WEST,Direction_NORTH);
        rightDirectionCommandMap.put(Direction_NORTH,Direction_EAST);
        rightDirectionCommandMap.put(Direction_EAST,Direction_SOUTH);
    }

    private void initalInputDirection(String direction) throws Exception {
        if(DirectionList.size()==0){
            DirectionList.add(Direction_SOUTH);
            DirectionList.add(Direction_NORTH);
            DirectionList.add(Direction_EAST);
            DirectionList.add(Direction_WEST);
        }
        if(!DirectionList.contains(direction)){
            throw new Exception("输入指定的方向错误，请重新检查后输入！");
        }
        this.curDirection=direction;
    }


    public String getCurDirection() {
        return curDirection;
    }

    public void setCurDirection(String curDirection) {
        this.curDirection = curDirection;
    }
}
