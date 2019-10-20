package TDD.MarsRover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarsCar {

    private AreaInfo areaInfo;
    private Point point;
    private Direction direction;
    private static Map<Object,Object> resultinfo=new HashMap<>();

    public MarsCar(AreaInfo areaInfo, Point point, Direction direction) throws Exception {
        this.areaInfo = areaInfo;
        this.point = point;
        this.direction = direction;
        if(point.getX()<0||point.getY()<0||point.getX()>=areaInfo.getLength()
                ||point.getY()>=areaInfo.getWidth()){
            throw new Exception("火星车的降落地点不在指定区域内！");
        }
    }



    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfo areaInfo) {
        this.areaInfo = areaInfo;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Map<Object,Object> execCommand(MarsCommand command) throws Exception {
        Map<Object,Object> result=new HashMap<>();
        String strcommand=command.getCommand();
        for(int i=0;i<strcommand.length();i++){
          result=MoveOrTurnCommand(this,strcommand.charAt(i));
        }
        return result;
    }

    private static Map<Object, Object> MoveOrTurnCommand(MarsCar marsCar, char command) throws Exception {
        String curdirection;
        String curd=marsCar.getDirection().getCurDirection();
        int curx=marsCar.getPoint().getX();
        int cury=marsCar.getPoint().getY();
        int afterx;
        int aftery;
        int desxory;
        switch (command){
            case 'l':
                System.out.println("cur direction: "+marsCar.getDirection().getCurDirection());
                curdirection=Direction.leftDirectionCommandMap.get(
                        marsCar.getDirection().getCurDirection());
                marsCar.setDirection(new Direction(curdirection));
                System.out.println("after direction: "+marsCar.getDirection().getCurDirection());
                System.out.println();
                break;
            case 'r':
                System.out.println("cur direction: "+marsCar.getDirection().getCurDirection());
                curdirection=Direction.rightDirectionCommandMap.get(
                        marsCar.getDirection().getCurDirection());
                marsCar.setDirection(new Direction(curdirection));
                System.out.println("after direction: "+marsCar.getDirection().getCurDirection());
                System.out.println();
                break;
            case 'f':
                System.out.println("cur direction: "+curd);
                System.out.println("cur positon: ("+ curx+", "+ cury+")");
                switch (marsCar.getDirection().getCurDirection()){
                    case "N":
                        desxory=cury+1;
                        if(desxory==marsCar.getAreaInfo().getWidth()){
                            desxory=0;
                        }
                        marsCar.getPoint().setY(desxory);
                        break;
                    case "S":
                        desxory=cury-1;
                        if(desxory<0){
                            desxory=marsCar.getAreaInfo().getWidth()-1;
                        }
                        marsCar.getPoint().setY(desxory);
                        break;
                    case "E":
                        desxory=curx+1;
                        if(desxory==marsCar.getAreaInfo().getLength()){
                            desxory=0;
                        }
                        marsCar.getPoint().setX(desxory);
                        break;
                    case "W":
                        desxory=curx-1;
                        if(desxory<0){
                            desxory=marsCar.getAreaInfo().getLength()-1;
                        }
                        marsCar.getPoint().setX(desxory);
                        break;
                    default:
                        break;
                }
                afterx=marsCar.getPoint().getX();
                aftery=marsCar.getPoint().getY();
                System.out.println("after positon: ("+ afterx+", "+ aftery+")");
                System.out.println();
                break;

            case 'b':
                System.out.println("cur direction: "+curd);
                System.out.println("cur positon: ("+ curx+", "+ cury+")");
                switch (marsCar.getDirection().getCurDirection()){
                    case "N":
                        desxory=cury-1;
                        if(desxory<0){
                            desxory=marsCar.getAreaInfo().getWidth()-1;
                        }
                        marsCar.getPoint().setY(desxory);
                        break;
                    case "S":
                        desxory=cury+1;
                        if(desxory==marsCar.getAreaInfo().getWidth()){
                            desxory=0;
                        }
                        marsCar.getPoint().setY(desxory);
                        break;
                    case "E":
                        desxory=curx-1;
                        if(desxory<0){
                            desxory=marsCar.getAreaInfo().getLength()-1;
                        }
                        marsCar.getPoint().setX(desxory);
                        break;
                    case "W":
                        desxory=curx+1;
                        if(desxory==marsCar.getAreaInfo().getLength()){
                            desxory=0;
                        }
                        marsCar.getPoint().setX(desxory);
                        break;
                    default:
                        break;
                }
                afterx=marsCar.getPoint().getX();
                aftery=marsCar.getPoint().getY();
                System.out.println("after positon: ("+ afterx+", "+ aftery+")");
                System.out.println();
                break;
            default:

                break;
        }
        resultinfo.put("Direction",marsCar.getDirection().getCurDirection());
        resultinfo.put("curx",marsCar.getPoint().getX());
        resultinfo.put("cury",marsCar.getPoint().getY());
        return resultinfo;

    }
}
